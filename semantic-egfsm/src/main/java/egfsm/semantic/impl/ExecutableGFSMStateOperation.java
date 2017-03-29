package egfsm.semantic.impl;

import efsm.semantic.algebra.EFSMAlgebra;
import efsm.semantic.impl.ExecuteStateOperation;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import syntax.fsm.State;
import syntax.fsm.Transition;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 14/03/17.
 */
public class ExecutableGFSMStateOperation implements ExecutableStateOperation {

    private final EFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra;
    private final State state;

    private final ExecuteStateOperation delegate;

    public ExecutableGFSMStateOperation(State state, EFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation> algebra) {
        this.state = state;
        this.algebra = algebra;
        delegate = new ExecuteStateOperation(state, algebra);
    }

    @Override
    public List<Transition> transitions(String event) {
        return state.getOutgoing().stream().filter(x -> {
            final ExecutableTransitionOperation sem = algebra.$(x);
            return x.getEvent().equals(event) && sem.hasGuard();
        }).collect(Collectors.toList());
    }
}
