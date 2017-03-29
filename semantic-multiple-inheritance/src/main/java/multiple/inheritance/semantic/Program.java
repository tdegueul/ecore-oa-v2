package multiple.inheritance.semantic;

import multiple.inheritance.semantic.impl.EvaluableMultipleInheritanceAlgebra;
import syntax.multiple.inheritance.B;
import syntax.multiple.inheritance.impl.CImpl;

/**
 * Created by mleduc on 29/03/17.
 */
public class Program {
    public static void main(String[] args) {
        final EvaluableMultipleInheritanceAlgebra algebra = new EvaluableMultipleInheritanceAlgebra() {
        };
        
        final B c = new CImpl();
        //c.setTime(Calendar.getInstance().getTime());
        //c.setValueA(12L);
        c.setValueB("string");

        algebra.$(c).show();
    }
}
