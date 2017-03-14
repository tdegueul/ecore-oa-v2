package efsm.semantic.impl;

import efsm.semantic.algebra.EFSMAlgebra;
import efsm.semantic.operation.ExecuteFSMOperation;
import efsm.semantic.operation.ExecuteStateOperation;
import efsm.semantic.operation.ExecuteTransitionOperation;
import fsm.State;
import fsm.Transition;
import syntax.efsm.FSM;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecutableEFSMAlgebra extends EFSMAlgebra<ExecuteFSMOperation, ExecuteStateOperation, ExecuteTransitionOperation> {

    @Override
    default efsm.semantic.impl.ExecuteFSMOperation fsm(FSM fsm) {
        return new efsm.semantic.impl.ExecuteFSMOperation(fsm, this);
    }

    @Override
    default efsm.semantic.impl.ExecuteStateOperation state(State state) {
        return new efsm.semantic.impl.ExecuteStateOperation(state, this);
    }

    @Override
    default efsm.semantic.impl.ExecuteTransitionOperation transition(Transition transition) {
        return new efsm.semantic.impl.ExecuteTransitionOperation(transition, this);
    }

}
