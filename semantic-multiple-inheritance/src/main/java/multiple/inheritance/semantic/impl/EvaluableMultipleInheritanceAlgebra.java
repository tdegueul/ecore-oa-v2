package multiple.inheritance.semantic.impl;

import multiple.inheritance.semantic.algebra.MultipleInheritanceAlgebraC;
import multiple.inheritance.semantic.operation.OperationA;
import multiple.inheritance.semantic.operation.OperationB;
import multiple.inheritance.semantic.operation.OperationC;
import syntax.multiple.inheritance.A;
import syntax.multiple.inheritance.B;
import syntax.multiple.inheritance.C;

/**
 * Created by mleduc on 29/03/17.
 */
public interface EvaluableMultipleInheritanceAlgebra extends MultipleInheritanceAlgebraC<OperationA, OperationB, OperationC> {
    @Override
    default OperationA a(A a) {
        return new OperationAImpl(a, this);
    }

    @Override
    default OperationB b(B b) {
        return new OperationBImpl(b, this);
    }

    @Override
    default OperationC c(C c) {
        return new OperationCImpl(c, this);
    }

    @Override
    default OperationB c_b(C c) {
        return new OperationCImpl(c, this);
    }

    @Override
    default OperationA c_a(C c) {
        return new OperationCImpl(c, this);
    }

}
