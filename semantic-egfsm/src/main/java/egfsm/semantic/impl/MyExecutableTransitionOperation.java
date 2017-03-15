package egfsm.semantic.impl;

import efsm.semantic.impl.ExecuteTransitionOperation;
import egfsm.semantic.algebra.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import expression.semantic.operation.EqualOperation;
import expression.semantic.operation.ExpressionOperation;
import syntax.gfsm.Transition;

/**
 * Created by mleduc on 14/03/17.
 */
public class MyExecutableTransitionOperation implements ExecutableTransitionOperation {
    private final Transition transition;
    private final EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation, ? extends ExpressionOperation, ? extends EqualOperation> algebra;

    private ExecuteTransitionOperation delegate;

    public MyExecutableTransitionOperation(Transition transition, EGFSMAlgebra<? extends ExecutableFSMOperation, ? extends ExecutableStateOperation, ? extends ExecutableTransitionOperation, ? extends ExpressionOperation, ? extends EqualOperation> algebra) {
        this.transition = transition;
        this.algebra = algebra;
        this.delegate = new ExecuteTransitionOperation(transition, algebra);
    }

    @Override
    public boolean hasGuard() {
        return algebra.$(transition.isGuard()).areEquals();
    }

    @Override
    public void fire() {
        delegate.fire();
    }
}
