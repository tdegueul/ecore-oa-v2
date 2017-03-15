package semantic.print.impl;


import semantic.print.algebra.ExpressionAlgebra;
import semantic.print.operation.EqualOperation;
import semantic.print.operation.ExpressionOperation;
import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Sum;

/**
 * Created by mleduc on 15/03/17.
 */
public interface PrintableExpressionAlgebra extends semantic.print.algebra.ExpressionAlgebra<ExpressionOperation, EqualOperation> {
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
        private final semantic.print.algebra.ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra;

        public ConstantExpressionOperation(Constant constant, semantic.print.algebra.ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra) {
            this.constant = constant;
            this.algebra = algebra;
        }


        @Override
        public String print() {
            return String.valueOf(this.constant.getValue());
        }
    }

    class SumExpressionOperation implements ExpressionOperation {
        private final Sum sum;
        private final ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra;

        public SumExpressionOperation(Sum sum, ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra) {
            this.sum = sum;
            this.algebra = algebra;
        }


        @Override
        public String print() {
            return "(" + algebra.$(sum.getLeft()).print() + " + " + algebra.$(sum.getRight()).print() + ")";
        }
    }

    class EqualEqualOperation implements EqualOperation {
        private final Equal equal;
        private final ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra;

        public EqualEqualOperation(Equal equal, ExpressionAlgebra<? extends ExpressionOperation, ? extends EqualOperation> algebra) {
            this.equal = equal;
            this.algebra = algebra;
        }


        @Override
        public String print() {
            return "(" + algebra.$(equal.getLeft()).print() + " == " + algebra.$(equal.getRight()).print() + ")";
        }
    }
}
