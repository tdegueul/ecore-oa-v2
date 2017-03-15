package efsm.semantic.impl;

import efsm.semantic.algebra.EFSMAlgebra;
import efsm.semantic.operation.ExecuteStateOperation;
import efsm.semantic.operation.ExecuteTransitionOperation;
import syntax.fsm.State;
import syntax.fsm.Transition;
import syntax.efsm.FSM;

import java.util.List;

/**
 * Created by mleduc on 14/03/17.
 */
public class ExecuteFSMOperation implements efsm.semantic.operation.ExecuteFSMOperation {
    private final FSM fsm;
    private final EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends ExecuteStateOperation, ? extends ExecuteTransitionOperation> algebra;


    public ExecuteFSMOperation(FSM fsm, EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends ExecuteStateOperation, ? extends ExecuteTransitionOperation> algebra) {
        this.fsm = fsm;
        this.algebra = algebra;
    }

    @Override
    public void execute(List<String> events) {
        for (String event : events) {
            final ExecuteStateOperation sem = algebra.$(fsm.getCurrentState());
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
