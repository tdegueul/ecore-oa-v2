package efsm.semantic.operation;

import fsm.State;

import java.util.List;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecuteFSMOperation {
    void execute(List<String> events);

    void setCurrentState(State to);
}
