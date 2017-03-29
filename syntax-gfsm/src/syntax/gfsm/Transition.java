package syntax.gfsm;

/**
 * Created by mleduc on 13/03/17.
 */
public class Transition extends syntax.fsm.Transition {
    private Boolean guard;

    public Boolean isGuard() {
        return guard;
    }

    public void setGuard(Boolean guard) {
        this.guard = guard;
    }
}
