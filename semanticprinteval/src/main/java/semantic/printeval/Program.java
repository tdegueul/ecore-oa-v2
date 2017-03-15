package semantic.printeval;

import semantic.print.impl.PrintableExpressionAlgebra;
import semantic.printeval.impl.PrintevalExpressionAlgebra;
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

        final PrintevalExpressionAlgebra printableExpressionAlgebra = new PrintevalExpressionAlgebra() {
        };
//        System.out.println(printableExpressionAlgebra.$(sum).print());
//        System.out.println(printableExpressionAlgebra.$(sum).result());

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
//        System.out.println(printableExpressionAlgebra.$(equal).print());
//        System.out.println(printableExpressionAlgebra.$(equal).areEquals());


        printableExpressionAlgebra.$(right).printeval();
        printableExpressionAlgebra.$(equal).printeval();
    }
}
