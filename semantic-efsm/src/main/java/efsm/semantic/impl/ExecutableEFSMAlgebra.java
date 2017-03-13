package efsm.semantic.impl;

import efsm.semantic.abstr.EFSMAlgebra;
import efsm.semantic.operation.ExecuteFSMOperation;
import efsm.semantic.operation.ExecuteStateOperation;
import efsm.semantic.operation.ExecuteTransitionOperation;
import fsm.State;
import fsm.Transition;
import syntax.efsm.FSM;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecutableEFSMAlgebra extends EFSMAlgebra<ExecuteFSMOperation, ExecuteStateOperation, ExecuteTransitionOperation> {

    @Override
    default ExecuteFSMOperation fsm(FSM fsm) {
        return new ExecuteFSMOperation(fsm, this);
    }

    @Override
    default ExecuteStateOperation state(State state) {
        return new ExecuteStateOperation(state, this);
    }

    @Override
    default ExecuteTransitionOperation transition(Transition transition) {
        return new ExecuteTransitionOperation(transition, this);
    }

    class ExecuteFSMOperation implements efsm.semantic.operation.ExecuteFSMOperation {
        private final FSM fsm;
        private final EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra;


        public ExecuteFSMOperation(FSM fsm, EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra) {
            this.fsm = fsm;
            this.algebra = algebra;
        }

        @Override
        public void execute(List<String> events) {
            for (String event : events) {
                final efsm.semantic.operation.ExecuteStateOperation sem = algebra.$(fsm.getCurrentState());
                List<Transition> transitions = sem.transitions(event);

                if (transitions.isEmpty()) {
                    System.out.println("DEADLOCK");
                    break;
                } else if (transitions.size() > 1) {
                    System.out.println("UNDETERMINISTIC");
                    break;
                } else {
                    algebra.$(transitions.get(0)).fire();
                }
            }
        }

        @Override
        public void setCurrentState(State to) {
            fsm.setCurrentState(to);
        }
    }

    public class ExecuteStateOperation implements efsm.semantic.operation.ExecuteStateOperation {

        protected final State state;
        private final EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra;


        public ExecuteStateOperation(State state, EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra) {
            this.state = state;
            this.algebra = algebra;
        }

        @Override
        public List<Transition> transitions(String event) {
            // do not use $ deleguation on model traversal when working at self level
            final List<Transition> outgoing = state.getOutgoing();
            return outgoing.stream().filter(transition -> Objects.equals(event, transition.getEvent()))
                    .collect(Collectors.toList());
        }
    }

    public class ExecuteTransitionOperation implements efsm.semantic.operation.ExecuteTransitionOperation {

        private final Transition transition;
        private final EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra;

        public ExecuteTransitionOperation(Transition transition, EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra) {
            this.transition = transition;
            this.algebra = algebra;

        }

        @Override
        public void fire() {

            // use $ deleguation when altering linked element (type group concistency).
            final efsm.semantic.operation.ExecuteFSMOperation sem = algebra.$(transition.getFsm());
            sem.setCurrentState(transition.getTo());
        }
    }
}
