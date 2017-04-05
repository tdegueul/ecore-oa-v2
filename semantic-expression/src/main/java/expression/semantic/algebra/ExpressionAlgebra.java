package expression.semantic.algebra;

import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Expression;
import syntax.expression.Sum;

/**
 * Created by mleduc on 15/03/17.
 */
public interface ExpressionAlgebra<TExpression, TEqual> {
    TExpression constant(Constant constant);
    TExpression sum(Sum sum);
    TEqual equal(Equal equal);

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
