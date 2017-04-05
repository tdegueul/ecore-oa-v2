package efsm.semantic.algebra;

import syntax.efsm.FSM;
import syntax.fsm.State;
import syntax.fsm.Transition;

/**
 * Created by mleduc on 13/03/17.
 */
public interface EFSMAlgebra<TFSM, TState, TTransition> {
    TFSM fsm(syntax.fsm.FSM fsm);
    TFSM fsm(FSM fsm);
    TState state(State state);
    TTransition transition(Transition transition);

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
