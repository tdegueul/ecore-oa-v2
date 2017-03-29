package multiple.inheritance.semantic.impl;

import multiple.inheritance.semantic.algebra.MultipleInheritanceAlgebra;
import multiple.inheritance.semantic.operation.OperationA;
import multiple.inheritance.semantic.operation.OperationB;
import multiple.inheritance.semantic.operation.OperationC;
import syntax.multiple.inheritance.B;

/**
 * Created by mleduc on 29/03/17.
 */
public class OperationBImpl implements OperationB {

    private final B b;
    private final MultipleInheritanceAlgebra<? extends OperationA, ? extends OperationB, ? extends OperationC> algebra;

    OperationBImpl(B b, MultipleInheritanceAlgebra<? extends OperationA, ? extends OperationB, ? extends OperationC> algebra) {
        this.b = b;
        this.algebra = algebra;
    }

    @Override
    public void show() {

        System.out.println("show level B");
        System.out.println(b.getValueB());
    }
}
