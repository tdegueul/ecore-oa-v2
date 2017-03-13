package efsm.semantic.operation;

import fsm.Transition;

import java.util.List;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecuteStateOperation {
    List<Transition> transitions(String event);
}
