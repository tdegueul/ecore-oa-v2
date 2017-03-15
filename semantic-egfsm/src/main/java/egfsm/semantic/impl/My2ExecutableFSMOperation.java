package egfsm.semantic.impl;

import efsm.semantic.impl.ExecuteFSMOperation;
import egfsm.semantic.algebra.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import expression.semantic.operation.EqualOperation;
import expression.semantic.operation.ExpressionOperation;
import syntax.efsm.FSM;
import syntax.fsm.State;

import java.util.List;

/**
 * Created by mleduc on 14/03/17.
 */
public class My2ExecutableFSMOperation implements ExecutableFSMOperation {
    private final FSM fsm;
    private final EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation, ? extends ExpressionOperation, ? extends EqualOperation> algebra;
    private final ExecuteFSMOperation delegate;

    public My2ExecutableFSMOperation(FSM fsm, EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation, ? extends ExpressionOperation, ? extends EqualOperation> algebra) {
        this.fsm = fsm;
        this.algebra = algebra;
        delegate = new ExecuteFSMOperation(fsm, algebra);
    }

    @Override
    public void execute(List<String> events) {
        delegate.execute(events);
    }

    @Override
    public void setCurrentState(State to) {
        delegate.setCurrentState(to);
    }
}
