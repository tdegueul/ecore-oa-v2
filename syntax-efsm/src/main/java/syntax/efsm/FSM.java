package syntax.efsm;

import syntax.fsm.State;

/**
 * Created by mleduc on 13/03/17.
 */
public class FSM extends syntax.fsm.FSM {
    private State currentState;


    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
