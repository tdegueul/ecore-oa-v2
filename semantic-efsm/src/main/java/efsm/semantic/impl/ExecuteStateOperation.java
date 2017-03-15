package efsm.semantic.impl;

import efsm.semantic.algebra.EFSMAlgebra;
import syntax.fsm.State;
import syntax.fsm.Transition;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 14/03/17.
 */
public class ExecuteStateOperation implements efsm.semantic.operation.ExecuteStateOperation {

    protected final State state;
    private final EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra;


    public ExecuteStateOperation(State state, EFSMAlgebra<? extends efsm.semantic.operation.ExecuteFSMOperation, ? extends efsm.semantic.operation.ExecuteStateOperation, ? extends efsm.semantic.operation.ExecuteTransitionOperation> algebra) {
        this.state = state;
        this.algebra = algebra;
    }

    @Override
    public List<Transition> transitions(String event) {
        // do not use $ deleguation on model traversal when working at self level
        final List<Transition> outgoing = state.getOutgoing();
        return outgoing.stream().filter(transition -> Objects.equals(event, transition.getEvent()))
                .collect(Collectors.toList());
    }
}
