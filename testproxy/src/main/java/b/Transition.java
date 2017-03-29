package b;

/**
 * Created by mleduc on 29/03/17.
 */
public interface Transition {
    String getEvent();

    void setEvent(String event);

    State getTo();

    State getFrom();

    void setTo(State to);

    void setFrom(State from);
}
