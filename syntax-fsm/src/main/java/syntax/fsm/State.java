package syntax.fsm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 13/03/17.
 */
public class State {
    private String name;
    private final List<Transition> outgoing = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transition> getOutgoing() {
        return outgoing;
    }
}
