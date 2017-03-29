package b.impl;

import b.FSM;
import b.State;
import b.Step;
import b.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 29/03/17.
 */
public class FSMImpl implements FSM {

    private final List<State> states = new ArrayList<>();
    private final List<Transition> transitions = new ArrayList<>();
    private State currentState;
    private final List<Step> steps = new ArrayList<>();

    @Override
    public List<State> getStates() {
        return states;
    }

    @Override
    public List<Transition> getTransitions() {
        return transitions;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void setCurrentState(State state) {
        this.currentState = state;
    }

    @Override
    public List<Step> getSteps() {
        return steps;
    }
}
