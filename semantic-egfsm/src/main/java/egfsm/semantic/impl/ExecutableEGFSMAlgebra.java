package egfsm.semantic.impl;

import egfsm.semantic.algebra.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import syntax.fsm.State;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecutableEGFSMAlgebra extends EGFSMAlgebra<ExecutableFSMOperation, ExecutableStateOperation, ExecutableTransitionOperation> {


    @Override
    default ExecutableTransitionOperation transition(syntax.gfsm.Transition transition) {
        return new ExecutableGFSMTransitionOperation(transition, this);
    }

    @Override
    default ExecutableFSMOperation fsm(syntax.efsm.FSM fsm) {
        return new ExecutableGFSMFSMOperation(fsm, this);
    }

    @Override
    default ExecutableStateOperation state(State state) {
        return new ExecutableGFSMStateOperation(state, this);
    }

    @Override
    default ExecutableTransitionOperation transition(syntax.fsm.Transition transition) {
        return new ExecutableFSMTransitionOperation(transition, this);
    }
}
