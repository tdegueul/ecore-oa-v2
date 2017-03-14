package efsm.semantic.impl;

import efsm.semantic.algebra.EFSMAlgebra;
import efsm.semantic.operation.*;
import efsm.semantic.operation.ExecuteStateOperation;
import fsm.Transition;

/**
 * Created by mleduc on 14/03/17.
 */
public class ExecuteTransitionOperation implements efsm.semantic.operation.ExecuteTransitionOperation {

    private final Transition transition;
    private final EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra;

    public ExecuteTransitionOperation(Transition transition, EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra) {
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
