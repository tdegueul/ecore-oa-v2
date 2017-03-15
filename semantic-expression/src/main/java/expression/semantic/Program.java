package expression.semantic;

import expression.semantic.impl.EvaluableExpressionAlgebra;
import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.expression.Sum;

/**
 * Created by mleduc on 15/03/17.
 */
public class Program {
    public static void main(String[] args) {
        Sum sum = new Sum();
        final Constant left = new Constant();
        left.setValue(1L);
        sum.setLeft(left);
        final Sum right = new Sum();
        final Constant left1 = new Constant();
        left1.setValue(2L);
        right.setLeft(left1);
        final Constant right1 = new Constant();
        right1.setValue(3L);
        right.setRight(right1);
        sum.setRight(right);

        final EvaluableExpressionAlgebra evaluableExpressionAlgebra = new EvaluableExpressionAlgebra() {
        };
        System.out.println(evaluableExpressionAlgebra.$(sum).result());

        final Equal equal = new Equal();
        equal.setRight(sum);
        final Sum left2 = new Sum();
        final Constant left3 = new Constant();
        left3.setValue(3L);
        left2.setLeft(left3);
        final Constant right2 = new Constant();
        right2.setValue(3L);
        left2.setRight(right2);
        equal.setLeft(left2);
        System.out.println(evaluableExpressionAlgebra.$(equal).areEquals());
    }
}
