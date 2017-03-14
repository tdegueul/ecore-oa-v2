package efsm.semantic.algebra;

import fsm.State;
import fsm.Transition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import syntax.efsm.FSM;

/**
 * Created by mleduc on 13/03/17.
 */
public interface EFSMAlgebra<TFSM, TState, TTransition> {

    default TFSM fsm(fsm.FSM fsm) {
        throw new NotImplementedException();
    }

    default TFSM fsm(FSM fsm) {
        throw new NotImplementedException();
    }

    default TState state(State state) {
        throw new NotImplementedException();
    }

    default TTransition transition(Transition transition) {
        throw new NotImplementedException();
    }

    default TFSM $(fsm.FSM fsm) {
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
