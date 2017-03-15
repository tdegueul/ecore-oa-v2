package syntax.fsm;

/**
 * Created by mleduc on 13/03/17.
 */
public class Transition {
    private String event;
    private State from;
    private State to;
    private FSM fsm;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public FSM getFsm() {
        return fsm;
    }

    public void setFsm(FSM fsm) {
        this.fsm = fsm;
    }
}
