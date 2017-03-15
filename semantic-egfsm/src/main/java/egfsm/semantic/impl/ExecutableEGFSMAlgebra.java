package egfsm.semantic.impl;

import egfsm.semantic.algebra.EGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import egfsm.semantic.operation.ExecutableStateOperation;
import egfsm.semantic.operation.ExecutableTransitionOperation;
import expression.semantic.impl.EvaluableExpressionAlgebra;
import expression.semantic.operation.EqualOperation;
import expression.semantic.operation.ExpressionOperation;
import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Sum;
import syntax.fsm.State;

/**
 * Created by mleduc on 13/03/17.
 */
public interface ExecutableEGFSMAlgebra extends EGFSMAlgebra<ExecutableFSMOperation, ExecutableStateOperation, ExecutableTransitionOperation, ExpressionOperation, EqualOperation> {


    @Override
    default ExecutableTransitionOperation transition(syntax.gfsm.Transition transition) {
        return new MyExecutableTransitionOperation(transition, this);
    }

    @Override
    default ExecutableFSMOperation fsm(syntax.efsm.FSM fsm) {
        return new My2ExecutableFSMOperation(fsm, this);
    }

    @Override
    default ExecutableStateOperation state(State state) {
        return new MyExecutableStateOperation(state, this);
    }

    @Override
    default ExecutableTransitionOperation transition(syntax.fsm.Transition transition) {
        return new My2ExecutableTransitionOperation(transition, this);
    }

    @Override
    default ExpressionOperation constant(Constant constant) {
        return new EvaluableExpressionAlgebra.ConstantExpressionOperation(constant, this);
    }

    @Override
    default ExpressionOperation sum(Sum sum) {
        return new EvaluableExpressionAlgebra.SumExpressionOperation(sum, this);
    }

    @Override
    default EqualOperation equal(Equal equal) {
        return new EvaluableExpressionAlgebra.EqualEqualOperation(equal, this);
    }
}
