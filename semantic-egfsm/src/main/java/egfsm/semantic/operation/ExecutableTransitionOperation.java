package egfsm.semantic.operation;

import efsm.semantic.operation.ExecuteTransitionOperation;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecutableTransitionOperation extends ExecuteTransitionOperation {
    boolean hasGuard();
}
