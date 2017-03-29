package b.impl;

import b.State;
import b.Step;

/**
 * Created by mleduc on 29/03/17.
 */
public class StepImpl implements Step {

    private State state;

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public State getState() {
        return state;
    }
}
