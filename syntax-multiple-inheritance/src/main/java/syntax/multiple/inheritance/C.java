package syntax.multiple.inheritance;

import java.util.Date;

/**
 * Created by mleduc on 29/03/17.
 */
public interface C extends A, B {
    Date getTime();
    void setTime(Date date);
}
