package multiple.inheritance.semantic.impl;

import multiple.inheritance.semantic.algebra.MultipleInheritanceAlgebra;
import multiple.inheritance.semantic.operation.OperationA;
import multiple.inheritance.semantic.operation.OperationB;
import multiple.inheritance.semantic.operation.OperationC;
import syntax.multiple.inheritance.A;

/**
 * Created by mleduc on 29/03/17.
 */
public class OperationAImpl implements OperationA {

    private final A a;
    private final MultipleInheritanceAlgebra<? extends OperationA, ? extends OperationB, ? extends OperationC> algebra;

    OperationAImpl(A a, MultipleInheritanceAlgebra<? extends OperationA, ? extends OperationB, ? extends OperationC> algebra) {
        this.a = a;
        this.algebra = algebra;
    }

    @Override
    public void show() {
        System.out.println("show level A");
        System.out.println(a.getValueA());
    }
}

