package syntax.multiple.inheritance.impl;

import syntax.multiple.inheritance.B;

/**
 * Created by mleduc on 29/03/17.
 */
public class BImpl implements B {
    private String valueB;

    @Override
    public String getValueB() {
        return valueB;
    }

    @Override
    public void setValueB(String valueB) {
        this.valueB = valueB;
    }
}
