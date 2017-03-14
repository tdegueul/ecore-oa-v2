package egfsm.semantic.impl;

import egfsm.semantic.algebra.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import egfsm.syntax.Transition;
import fsm.State;

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


}
