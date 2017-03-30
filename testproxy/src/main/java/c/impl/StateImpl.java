package c.impl;

import c.State;
import c.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 29/03/17.
 */
public class StateImpl implements State {

    private String name;
    private final List<Transition> outgoing = new ArrayList<>();

    @Override
    public String getName() {
        return name + "c";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Transition> getOutgoing() {
        return outgoing;
    }
}
