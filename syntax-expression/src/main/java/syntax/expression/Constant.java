package syntax.expression;

/**
 * Created by mleduc on 15/03/17.
 */
public class Constant extends Expression {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
