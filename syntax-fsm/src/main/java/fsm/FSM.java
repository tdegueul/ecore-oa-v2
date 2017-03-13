package fsm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 13/03/17.
 */
public class FSM {
    private final List<State> states = new ArrayList<>();
    private final List<Transition> transitions = new ArrayList<Transition>();
    private State initialState = null;

    public List<State> getStates() {
        return states;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }
}
