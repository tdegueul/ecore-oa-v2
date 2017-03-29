package b;

import java.util.List;

/**
 * Created by mleduc on 29/03/17.
 */
public interface State {
    String getName();

    void setName(String name);

    List<Transition> getOutgoing();
}
