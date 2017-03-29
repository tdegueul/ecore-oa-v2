package multiple.inheritance.semantic.impl;

import multiple.inheritance.semantic.algebra.MultipleInheritanceAlgebraC;
import multiple.inheritance.semantic.operation.OperationA;
import multiple.inheritance.semantic.operation.OperationB;
import multiple.inheritance.semantic.operation.OperationC;
import syntax.multiple.inheritance.C;

/**
 * Created by mleduc on 29/03/17.
 */
public class OperationCImpl implements OperationC {

    private final C c;
    private final MultipleInheritanceAlgebraC<? extends OperationA, ? extends OperationB, ? extends OperationC> algebra;
    private final OperationB delegateB;
    private final OperationA delegateA;

    OperationCImpl(C c, MultipleInheritanceAlgebraC<? extends OperationA, ? extends OperationB, ? extends OperationC> algebra) {
        this.c = c;
        this.algebra = algebra;
        this.delegateB = new OperationBImpl(c, algebra);
        this.delegateA = new OperationAImpl(c, algebra);
    }

    @Override
    public void show() {
        // removing ambiguity by delegation.
        // do we allow
        System.out.println("show level C");
        this.delegateB.show();
    }
}
