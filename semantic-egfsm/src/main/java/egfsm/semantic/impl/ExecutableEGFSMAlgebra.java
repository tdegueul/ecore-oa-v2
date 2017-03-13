package egfsm.semantic.impl;

import efsm.semantic.abstr.EFSMAlgebra;
import efsm.semantic.impl.ExecutableEFSMAlgebra;
import egfsm.semantic.abstr.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import egfsm.syntax.Transition;
import fsm.State;
import syntax.efsm.FSM;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecutableEGFSMAlgebra extends EGFSMAlgebra<ExecutableFSMOperation, ExecutableStateOperation, ExecutableTransitionOperation> {


    @Override
    default ExecutableTransitionOperation transition(Transition transition) {
        return new MyExecutableTransitionOperation(transition, this);
    }

    @Override
    default ExecutableFSMOperation fsm(syntax.efsm.FSM fsm) {
        return new My2ExecutableFSMOperation(fsm, this);
    }

    @Override
    default ExecutableStateOperation state(State state) {
        return new MyExecutableStateOperation(state, this);
    }

    @Override
    default ExecutableTransitionOperation transition(fsm.Transition transition) {
        return new My2ExecutableTransitionOperation(transition, this);
    }


    class MyExecutableTransitionOperation implements ExecutableTransitionOperation {
        private final Transition transition;
        private final EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra;

        private ExecutableEFSMAlgebra.ExecuteTransitionOperation delegate;

        public MyExecutableTransitionOperation(Transition transition, EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra) {
            this.transition = transition;
            this.algebra = algebra;
            this.delegate = new ExecutableEFSMAlgebra.ExecuteTransitionOperation(transition, algebra);
        }

        @Override
        public boolean hasGuard() {
            return transition.isGuard();
        }

        @Override
        public void fire() {
            delegate.fire();
        }
    }

    class My2ExecutableFSMOperation implements ExecutableFSMOperation {
        private final FSM fsm;
        private final EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra;
        private final ExecutableEFSMAlgebra.ExecuteFSMOperation delegate;

        public My2ExecutableFSMOperation(FSM fsm, EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra) {
            this.fsm = fsm;
            this.algebra = algebra;
            delegate = new ExecutableEFSMAlgebra.ExecuteFSMOperation(fsm, algebra);
        }

        @Override
        public void execute(List<String> events) {
            delegate.execute(events);
        }

        @Override
        public void setCurrentState(State to) {
            delegate.setCurrentState(to);
        }
    }

    class MyExecutableStateOperation implements ExecutableStateOperation {

        private final EFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra;
        private final State state;

        private final ExecutableEFSMAlgebra.ExecuteStateOperation delegate;

        public MyExecutableStateOperation(State state, EFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra) {
            this.state = state;
            this.algebra = algebra;
            delegate = new ExecutableEFSMAlgebra.ExecuteStateOperation(state, algebra);
        }

        @Override
        public List<fsm.Transition> transitions(String event) {
            return state.getOutgoing().stream().filter(x -> {
                final ExecutableTransitionOperation sem = algebra.$(x);
                return x.getEvent().equals(event) && sem.hasGuard();
            }).collect(Collectors.toList());
        }
    }

    class My2ExecutableTransitionOperation implements ExecutableTransitionOperation {
        private final fsm.Transition transition;
        private final EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra;
        private ExecutableEFSMAlgebra.ExecuteTransitionOperation delegate;

        public My2ExecutableTransitionOperation(fsm.Transition transition, EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra) {
            this.transition = transition;
            this.algebra = algebra;
            this.delegate = new ExecutableEFSMAlgebra.ExecuteTransitionOperation(transition, algebra);
        }

        @Override
        public boolean hasGuard() {
            return false;
        }

        @Override
        public void fire() {
            delegate.fire();
        }
    }
}
