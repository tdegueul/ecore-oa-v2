package expression.semantic.algebra;

import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Expression;
import syntax.expression.Sum;

/**
 * Created by mleduc on 15/03/17.
 */
public interface ExpressionAlgebra<TExpression, TEqual> {
    default TExpression constant(Constant constant) {
        throw new UnsupportedOperationException();
    }

    default TExpression sum(Sum sum) {
        throw new UnsupportedOperationException();
    }

    default TEqual equal(Equal equal) { throw new UnsupportedOperationException(); }

    default TExpression $(Expression expression) {
        if (expression instanceof Constant) {
            return constant((Constant) expression);
        }

        if (expression instanceof Sum) {
            return sum((Sum) expression);
        }

        throw new RuntimeException("Unknow Expression " + expression);
    }

    default TEqual $(Equal equal) {
        return equal(equal);
    }
}
