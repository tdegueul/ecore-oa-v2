/**
 * Created by mleduc on 28/03/17.
 */

interface Visitor<U> {
    <T> T visit(U u);
}

abstract class Expression {

}

class Mul extends Expression {
    public Expression left;
    public Expression right;

    public <T> T accept(Visitor<Mul> mulEval) {
        return mulEval.visit(this);
    }
}

class Constant extends Expression {
    public long value;
}

class MulVisitor implements Visitor<Mul> {

    @Override
    public <T> T visit(Mul mul) {
        return null;
    }
}

class ConstantEval implements Visitor<Constant> {
    @Override
    public <T> T visit(Constant constant) {
        return null;
    }
}

/*class ExpressionVisitor implements ConstantEval, MulVisitor {

}

class MulEval implements MulVisitor {

    @Override
    public <T> T visit(Mul mul) {
        return mul.accept(this);
    }
}*/

public class Test {
}
