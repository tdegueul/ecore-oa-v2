package egfsm.semantic.algebra;

import syntax.fsm.Transition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by mleduc on 13/03/17.
 */
public interface EGFSMAlgebra<TFSM, TState, TTransition> extends efsm.semantic.algebra.EFSMAlgebra<TFSM, TState, TTransition> {
    default TTransition transition(syntax.gfsm.Transition transition) {
        throw new NotImplementedException();
    }

    @Override
    default TTransition $(Transition transition) {
        if (transition instanceof syntax.gfsm.Transition) {
            return transition((syntax.gfsm.Transition) transition);
        }
        return transition(transition);
    }
}
