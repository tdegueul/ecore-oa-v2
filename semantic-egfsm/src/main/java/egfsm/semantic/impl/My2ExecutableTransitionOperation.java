package egfsm.semantic.impl;

import efsm.semantic.impl.ExecuteTransitionOperation;
import egfsm.semantic.algebra.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import expression.semantic.operation.EqualOperation;
import expression.semantic.operation.ExpressionOperation;
import syntax.fsm.Transition;

/**
 * Created by mleduc on 14/03/17.
 */
public class My2ExecutableTransitionOperation implements ExecutableTransitionOperation {
    private final Transition transition;
    private final EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation, ? extends ExpressionOperation, ? extends EqualOperation> algebra;
    private ExecuteTransitionOperation delegate;

    public My2ExecutableTransitionOperation(Transition transition, EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation, ? extends ExpressionOperation, ? extends EqualOperation> algebra) {
        this.transition = transition;
        this.algebra = algebra;
        this.delegate = new ExecuteTransitionOperation(transition, algebra);
    }

    @Override
    public boolean hasGuard() {
        return false;
    }

    @Override
    public void fire() {
        delegate.fire();
    }
}
