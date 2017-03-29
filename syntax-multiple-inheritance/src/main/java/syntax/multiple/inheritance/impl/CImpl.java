package syntax.multiple.inheritance.impl;

import syntax.multiple.inheritance.C;

import java.util.Date;

/**
 * Created by mleduc on 29/03/17.
 */
public class CImpl implements C {

    private Long valueA;
    private String valueB;
    private Date time;

    @Override
    public Long getValueA() {
        return valueA;
    }

    @Override
    public void setValueA(Long valueA) {
        this.valueA = valueA;
    }

    @Override
    public String getValueB() {
        return valueB;
    }

    @Override
    public void setValueB(String valueB) {
        this.valueB = valueB;
    }

    @Override
    public Date getTime() {
        return time;
    }

    @Override
    public void setTime(Date time) {
        this.time = time;
    }
}
