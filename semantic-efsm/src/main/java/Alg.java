/**
 * Created by mleduc on 28/03/17.
 */
public interface Alg<A, B extends A> {

    // Son <: Parent

    A parent(Parent p);

    B son(Son son);

    default <Z extends A> Z $(Parent p) {
        if (p instanceof Son) {
            //return son((Son) p);
        }
        //return parent(p);
        return null;
    }

    default B $(Son s) {
        return son(s);
    }
}

interface OpParent {
    String onParent();
}

interface OpSon extends OpParent {

    // onParent by inheritance
    Integer onSon();
}

interface ConcreteAlgebra extends
        Alg<OpParent, OpSon> {
    @Override
    default OpParent parent(Parent p) {
        return new OpParent() {
            @Override
            public String onParent() {
                return p.getName();
            }
        };
    }

    @Override
    default OpSon son(Son son) {
        return new OpSon() {
            @Override
            public Integer onSon() {
                return son.getValue();
            }

            @Override
            public String onParent() {
                return son.getName();
            }
        };
    }
}
