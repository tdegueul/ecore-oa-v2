package semantic.printeval.impl;

import expression.semantic.impl.EvaluableExpressionAlgebra;
import semantic.print.impl.PrintableExpressionAlgebra;
import semantic.printeval.algebra.ExpressionAlgebra;
import semantic.printeval.operation.EqualOperation;
import semantic.printeval.operation.ExpressionOperation;
import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Sum;

/**
 * Created by mleduc on 15/03/17.
 */
public class PrintevalExpressionAlgebra implements ExpressionAlgebra<ExpressionOperation, EqualOperation> {
    @Override
    public ExpressionOperation constant(Constant constant) {
        return new ConstantExpressionOperation(constant, this);
    }

    @Override
    public ExpressionOperation sum(Sum sum) {
        return new SumExpressionOperation(sum, this);
    }

    @Override
    public EqualOperation equal(Equal equal) {
        return new EqualOperation(equal, this);
    }

    private static class ConstantExpressionOperation implements ExpressionOperation {
        private final Constant constant;
        private final ExpressionAlgebra<? extends ExpressionOperation, ? extends semantic.printeval.operation.EqualOperation> algebra;
        private final EvaluableExpressionAlgebra.ConstantExpressionOperation evalDelegate;
        private final PrintableExpressionAlgebra.ConstantExpressionOperation printDelegate;


        public ConstantExpressionOperation(Constant constant, ExpressionAlgebra<? extends ExpressionOperation, ? extends semantic.printeval.operation.EqualOperation> algebra) {
            this.constant = constant;
            this.algebra = algebra;
            this.evalDelegate = new EvaluableExpressionAlgebra.ConstantExpressionOperation(constant, algebra);
            this.printDelegate = new PrintableExpressionAlgebra.ConstantExpressionOperation(constant, algebra);
        }

        @Override
        public String print() {
            return printDelegate.print();
        }

        @Override
        public Long result() {
            return evalDelegate.result();
        }

        @Override
        public void printeval() {
            System.out.println(this.print());
            System.out.println(this.result());

        }
    }

    private static class SumExpressionOperation implements ExpressionOperation {

        private final ExpressionAlgebra<? extends ExpressionOperation, ? extends semantic.printeval.operation.EqualOperation> algebra;
        private final Sum sum;
        private final EvaluableExpressionAlgebra.SumExpressionOperation evalDelegate;
        private final PrintableExpressionAlgebra.SumExpressionOperation printDelegate;


        public SumExpressionOperation(Sum sum, ExpressionAlgebra<? extends ExpressionOperation, ? extends semantic.printeval.operation.EqualOperation> algebra) {
            this.sum = sum;
            this.algebra = algebra;
            this.evalDelegate = new EvaluableExpressionAlgebra.SumExpressionOperation(sum, algebra);
            this.printDelegate = new PrintableExpressionAlgebra.SumExpressionOperation(sum, algebra);
        }

        @Override
        public String print() {
            return printDelegate.print();
        }

        @Override
        public Long result() {
            return evalDelegate.result();
        }

        @Override
        public void printeval() {
            algebra.$(sum.getLeft()).printeval();
            algebra.$(sum.getRight()).printeval();
            System.out.println(this.print());
            System.out.println(this.result());
        }
    }

    private static class EqualOperation implements semantic.printeval.operation.EqualOperation {
        private final ExpressionAlgebra<? extends ExpressionOperation, ? extends semantic.printeval.operation.EqualOperation> algebra;
        private final Equal equal;
        private final EvaluableExpressionAlgebra.EqualEqualOperation evalDelegate;
        private final PrintableExpressionAlgebra.EqualEqualOperation printDelegate;

        public EqualOperation(Equal equal, ExpressionAlgebra<? extends ExpressionOperation, ? extends semantic.printeval.operation.EqualOperation> algebra) {
            this.equal = equal;
            this.algebra = algebra;
            this.evalDelegate = new EvaluableExpressionAlgebra.EqualEqualOperation(equal, algebra);
            this.printDelegate = new PrintableExpressionAlgebra.EqualEqualOperation(equal, algebra);

        }

        @Override
        public String print() {
            return printDelegate.print();
        }

        @Override
        public boolean areEquals() {
            return evalDelegate.areEquals();
        }

        @Override
        public void printeval() {
            algebra.$(equal.getLeft()).printeval();
            algebra.$(equal.getRight()).printeval();
            System.out.println(this.print());
            System.out.println(this.areEquals());
        }
    }
}
