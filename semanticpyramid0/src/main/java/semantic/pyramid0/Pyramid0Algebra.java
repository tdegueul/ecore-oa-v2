package semantic.pyramid0;

import syntax.pyramid.level0.A;
import syntax.pyramid.level0.B;
import syntax.pyramid.level0.C;

/**
 * Created by mleduc on 29/03/17.
 */
public interface Pyramid0Algebra<AT, BT, CT> {
    AT a(A a);

    BT b(B b);

    CT c(C c);

    default AT $(A a) {
        return a(a);
    }

    default BT $(B b) {
        return b(b);
    }

    default CT $(C c) {
        return c(c);
    }
}
