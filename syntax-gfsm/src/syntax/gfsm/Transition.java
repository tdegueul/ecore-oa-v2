package syntax.gfsm;

import syntax.expression.Equal;

/**
 * Created by mleduc on 13/03/17.
 */
public class Transition extends syntax.fsm.Transition {
    private Equal guard;

    public Equal isGuard() {
        return guard;
    }

    public void setGuard(Equal guard) {
        this.guard = guard;
    }
}
