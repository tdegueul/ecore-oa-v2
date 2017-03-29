package a;

import java.util.List;

/**
 * Created by mleduc on 29/03/17.
 */
public interface FSM {
    List<a.State> getStates();
    List<a.Transition> getTransitions();

}
