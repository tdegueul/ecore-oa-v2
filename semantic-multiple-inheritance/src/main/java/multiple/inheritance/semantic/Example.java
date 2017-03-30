package multiple.inheritance.semantic;

/**
 * Created by mleduc on 30/03/17.
 */

interface A {
}

interface B extends A {
}

interface C extends A {
}

interface D extends C, B {
}

interface MyAlgebra<AT, BT, CT, DT> {

    // A level operations
    AT a(A a);

    AT b_a(B b);

    AT c_a(C c);

    AT d_a(D d);

    // B level operations
    BT b(B b);

    BT d_b(D d);

    // C level operations
    CT c(C c);

    CT d_c(D d);

    // D level operations
    DT d(D d);

    // $ methods

    // dispatch to level A
    default AT $(A a) {
        if (a instanceof D) return d_a((D) a);
        if (a instanceof C) return c_a((C) a);
        if (a instanceof B) return b_a((B) a);
        return a(a);
    }

    // dispatch to level B
    default BT $(B b) {
        if (b instanceof D) return d_b((D) b);
        return b(b);
    }


    // dispatch to level C
    default CT $(C c) {
        if (c instanceof D) return d_c((D) c);
        return c(c);
    }

    // dispatch to level C
    default DT $(D d) {
        return d(d);
    }

}

// print algebra interfaces
interface PrintA {
    String print();
}

interface PrintB extends PrintA {
    String printForB();
}

interface PrintC extends PrintA {
    String printForC();
}

interface PrintD extends PrintB, PrintC {

}

class PrintAImpl implements PrintA {

    @Override
    public String print() {
        return "A";
    }
}

class PrintBImpl implements PrintB {

    @Override
    public String print() {
        return "B";
    }

    @Override
    public String printForB() {
        return "BB";
    }
}

class PrintCImpl implements PrintC {

    @Override
    public String print() {
        return "C";
    }

    @Override
    public String printForC() {
        return "CC";
    }
}

class PrintDImpl implements PrintD {

    @Override
    public String print() {
        return "D";
    }

    @Override
    public String printForB() {
        return "DB";
    }

    @Override
    public String printForC() {
        return "DC";
    }
}


interface PrintMyAlgebra extends MyAlgebra<PrintA, PrintB, PrintC, PrintD> {

    // A level
    @Override
    default PrintA a(A a) {
        return new PrintAImpl();
    }

    @Override
    default PrintA b_a(B b) {
        return new PrintAImpl();
    }

    @Override
    default PrintA c_a(C c) {
        return new PrintCImpl();
    }

    @Override
    default PrintA d_a(D d) {
        return new PrintDImpl();
    }


// B level
    @Override
    default PrintB b(B b) {
        return new PrintBImpl();
    }

    @Override
    default PrintB d_b(D d) {
        return new PrintDImpl();
    }

    // C level
    @Override
    default PrintC c(C c) {
        return new PrintCImpl();
    }

    @Override
    default PrintC d_c(D d) {
        return new PrintCImpl();
    }

    // D level
    @Override
    default PrintD d(D d) {
        return new PrintDImpl();
    }
}

