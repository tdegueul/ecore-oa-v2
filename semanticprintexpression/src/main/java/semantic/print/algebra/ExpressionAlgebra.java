package semantic.print.algebra;

/**
 * Created by mleduc on 15/03/17.
 */
public interface ExpressionAlgebra<TExpression, TEqual> {
    TExpression constant(syntax.expression.Constant constant);
    TExpression sum(syntax.expression.Sum sum);
    TEqual equal(syntax.expression.Equal equal);

    default TExpression $(syntax.expression.Expression expression) {
        if (expression instanceof syntax.expression.Constant) {
            return constant((syntax.expression.Constant) expression);
        }

        if (expression instanceof syntax.expression.Sum) {
            return sum((syntax.expression.Sum) expression);
        }

        throw new RuntimeException("Unknow Expression " + expression);
    }

    default TEqual $(syntax.expression.Equal equal) {
        return equal(equal);
    }
}
