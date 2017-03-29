package semantic.pyramid0.impl;

import semantic.pyramid0.Pyramid0Algebra;
import semantic.pyramid0.operation.OperationA;
import semantic.pyramid0.operation.OperationB;
import semantic.pyramid0.operation.OperationC;
import syntax.pyramid.level0.A;
import syntax.pyramid.level0.B;
import syntax.pyramid.level0.C;

/**
 * Created by mleduc on 29/03/17.
 */
public interface ExecutablePyramid0Algebra extends Pyramid0Algebra<OperationA, OperationB, OperationC> {
    @Override
    OperationA a(A a);

    @Override
    OperationB b(B b);

    @Override
    OperationC c(C c);
}
