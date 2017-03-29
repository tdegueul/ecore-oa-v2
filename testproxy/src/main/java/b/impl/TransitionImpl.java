package b.impl;

import b.State;
import b.Transition;

/**
 * Created by mleduc on 29/03/17.
 */
public class TransitionImpl implements Transition {

    private String event;
    private State to;
    private State from;

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public State getTo() {
        return to;
    }

    @Override
    public State getFrom() {
        return from;
    }

    @Override
    public void setTo(State to) {
        this.to = to;
    }

    @Override
    public void setFrom(State from) {
        this.from = from;
    }
}
