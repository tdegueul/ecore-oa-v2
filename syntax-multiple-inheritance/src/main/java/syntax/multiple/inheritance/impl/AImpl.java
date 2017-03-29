package syntax.multiple.inheritance.impl;

import syntax.multiple.inheritance.A;

/**
 * Created by mleduc on 29/03/17.
 */
public class AImpl implements A {
    private Long valueA;

    @Override
    public Long getValueA() {
        return valueA;
    }

    @Override
    public void setValueA(Long value) {
        this.valueA = value;
    }
}
