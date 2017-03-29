package a.impl;

import a.FSM;
import a.State;
import a.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 29/03/17.
 */
public class FSMImpl implements FSM {

    private final List<State> states = new ArrayList<>();
    private final List<Transition> transitions = new ArrayList<>();

    @Override
    public List<State> getStates() {
        return states;
    }

    @Override
    public List<Transition> getTransitions() {
        return transitions;
    }
}
