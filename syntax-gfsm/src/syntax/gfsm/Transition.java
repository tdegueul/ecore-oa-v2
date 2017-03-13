package syntax.gfsm;

/**
 * Created by mleduc on 13/03/17.
 */
public class Transition extends fsm.Transition {
    private boolean guard;

    public boolean isGuard() {
        return guard;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }
}
