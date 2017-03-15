package syntax.expression;

/**
 * Created by mleduc on 15/03/17.
 */
public class Sum extends Expression {
    private Expression left;
    private Expression right;

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
}
