package multiple.inheritance.semantic.algebra;

import syntax.multiple.inheritance.A;
import syntax.multiple.inheritance.B;
import syntax.multiple.inheritance.C;

/**
 * Created by mleduc on 29/03/17.
 */
interface MultipleInheritanceAlgebraA<AT> {
    AT a(A a);

    /* on $ method by class in the metamodel.
     * if a class has subclasses, a dispatch is done at the class level.
     *
     * The more children a class have, the more case have to be inspected in order to do the dispatch.
     * In the other hand, the dispatch is only done at the runtime type of the dispatch element, which will
     * probably lower the dispatching time
     *
     * TODO: Idea #1 Do we rebuild the dispatch on children algebras in order to avoid ugly try/catch ?
     * TODO: Idea #2 a $$ method could be use to return a boolean. true if the type is know on the hierarchy.
     *  of course we have to be careful with the order of resolution otherwise some elements could be called with the
     *  semantics of one of their (concrete) parents.
     */
    default AT $(A a) {
        return a(a);
    }
}

interface MultipleInheritanceAlgebraB<BT> {
    BT b(B b);

    /* on $ method by class in the metamodel.
     * if a class has subclasses, a dispatch is done at the class level.
     *
     * The more children a class have, the more case have to be inspected in order to do the dispatch.
     * In the other hand, the dispatch is only done at the runtime type of the dispatch element, which will
     * probably lower the dispatching time
     *
     * TODO: Idea #1 Do we rebuild the dispatch on children algebras in order to avoid ugly try/catch ?
     * TODO: Idea #2 a $$ method could be use to return a boolean. true if the type is know on the hierarchy.
     *  of course we have to be careful with the order of resolution otherwise some elements could be called with the
     *  semantics of one of their (concrete) parents.
     */
    default BT $(B b) {
        return b(b);
    }
}




// one abstract type by class in the metamodel
public interface MultipleInheritanceAlgebra<AT, BT, CT> extends MultipleInheritanceAlgebraA<AT>, MultipleInheritanceAlgebraB<BT> {

    // one method by concrete class
    CT c(C c);

    // one method by parent of a class in the hierarchy
    BT c_b(C b);

    AT c_a(C a);

    @Override
    default AT $(A a) {
        if (a instanceof C) return c_a((C) a);
        else return MultipleInheritanceAlgebraA.super.$(a);
    }

    default BT $(B b) {
        if (b instanceof C) return c_b((C) b);
        return MultipleInheritanceAlgebraB.super.$(b);
    }

    default CT $(C c) {
        return c(c);
    }
}
