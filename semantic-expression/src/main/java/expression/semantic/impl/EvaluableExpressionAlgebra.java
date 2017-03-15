package expression.semantic.impl;


import expression.semantic.operation.EqualOperation;
import expression.semantic.operation.ExpressionOperation;
import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Sum;

import java.util.Objects;

/**
 * Created by mleduc on 15/03/17.
 */
public interface EvaluableExpressionAlgebra extends expression.semantic.algebra.ExpressionAlgebra<expression.semantic.operation.ExpressionOperation, expression.semantic.operation.EqualOperation> {
    @Override
    default ExpressionOperation constant(Constant constant) {
        return new ConstantExpressionOperation(constant, this);
    }

    @Override
    default ExpressionOperation sum(Sum sum) {
        return new SumExpressionOperation(sum, this);
    }

    @Override
    default EqualOperation equal(Equal equal) {
        return new EqualEqualOperation(equal, this);
    }

    class ConstantExpressionOperation implements ExpressionOperation {
        private final Constant constant;
        private final expression.semantic.algebra.ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra;

        public ConstantExpressionOperation(Constant constant, expression.semantic.algebra.ExpressionAlgebra<? extends expression.semantic.operation.ExpressionOperation, ? extends expression.semantic.operation.EqualOperation> algebra) {
            this.constant = constant;
            this.algebra = algebra;
        }

        @Override
        public Long result() {
            return constant.getValue();
        }
    }

    class SumExpressionOperation implements ExpressionOperation {
        private final Sum sum;
        private final expression.semantic.algebra.ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra;

        public SumExpressionOperation(Sum sum, expression.semantic.algebra.ExpressionAlgebra<? extends expression.semantic.operation.ExpressionOperation, ? extends expression.semantic.operation.EqualOperation> algebra) {
            this.sum = sum;
            this.algebra = algebra;
        }

        @Override
        public Long result() {
            return algebra.$(sum.getLeft()).result() + algebra.$(sum.getRight()).result();
        }
    }

    class EqualEqualOperation implements EqualOperation {
        private final Equal equal;
        private final expression.semantic.algebra.ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra;

        public EqualEqualOperation(Equal equal, expression.semantic.algebra.ExpressionAlgebra<? extends expression.semantic.operation.ExpressionOperation, ? extends expression.semantic.operation.EqualOperation> algebra) {
            this.equal = equal;
            this.algebra = algebra;
        }

        @Override
        public boolean areEquals() {
            return Objects.equals(algebra.$(equal.getLeft()).result(), algebra.$(equal.getRight()).result());
        }
    }
}
