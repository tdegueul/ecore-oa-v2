package c;

import java.util.List;

/**
 * Created by mleduc on 29/03/17.
 */
public interface FSM {
    List<State> getStates();

    List<Transition> getTransitions();

    State getCurrentState();

    void setCurrentState(State state);

    List<Step> getSteps();

}
