package efsm.semantic.algebra;

import syntax.efsm.FSM;
import syntax.fsm.State;
import syntax.fsm.Transition;

/**
 * Created by mleduc on 13/03/17.
 */
public interface EFSMAlgebra<TFSM, TState, TTransition> {

    default TFSM fsm(syntax.fsm.FSM fsm) {
        throw new UnsupportedOperationException();
    }

    default TFSM fsm(FSM fsm) {
        throw new UnsupportedOperationException();
    }

    default TState state(State state) {
        throw new UnsupportedOperationException();
    }

    default TTransition transition(Transition transition) {
        throw new UnsupportedOperationException();
    }

    default TFSM $(syntax.fsm.FSM fsm) {
        if (fsm instanceof syntax.efsm.FSM) {
            return fsm((syntax.efsm.FSM) fsm);
        }
        return fsm(fsm);
    }

    default TState $(State state) {
        return state(state);
    }

    default TTransition $(Transition transition) {
        return transition(transition);
    }

}
