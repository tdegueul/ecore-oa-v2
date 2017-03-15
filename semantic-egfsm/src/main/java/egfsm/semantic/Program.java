package egfsm.semantic;

import egfsm.semantic.impl.ExecutableEGFSMAlgebra;
import syntax.expression.Constant;
import syntax.expression.Equal;
import syntax.gfsm.Transition;
import syntax.fsm.State;
import syntax.efsm.FSM;

import java.util.Arrays;

/**
 * Created by mleduc on 13/03/17.
 */
public class Program {
    public static void main(String[] args) {
        final FSM fsm = initModel();

        new ExecutableEGFSMAlgebra() {
        }.$(fsm).execute(Arrays.asList("a", "b", "a"));

        System.out.println(fsm.getCurrentState().getName());
    }

    private static FSM initModel() {
        final FSM fsm = new FSM();

        final State state1 = new State();
        state1.setName("s1");
        final State state2 = new State();
        state2.setName("s2");
        fsm.getStates().add(state1);
        fsm.getStates().add(state2);
        final Transition t1 = new Transition();
        t1.setEvent("a");
        final Equal guard = new Equal();
        final Constant left = new Constant();
        left.setValue(1L);
        guard.setLeft(left);
        final Constant right = new Constant();
        right.setValue(1L);
        guard.setRight(right);
        t1.setGuard(guard);


        t1.setFrom(state1);
        t1.setTo(state2);
        state1.getOutgoing().add(t1);

        final Transition t2 = new Transition();
        t2.setEvent("b");
        final Equal guard1 = new Equal();
        final Constant left1 = new Constant();
        left1.setValue(1L);
        guard1.setLeft(left1);
        final Constant right1 = new Constant();
        right1.setValue(2L);
        guard1.setRight(right1);
        t2.setGuard(guard1);

        t2.setFrom(state2);
        t2.setTo(state1);
        state2.getOutgoing().add(t2);


        final Transition t3 = new Transition();
        t3.setEvent("b");
        final Equal guard2 = new Equal();
        final Constant left2 = new Constant();
        left2.setValue(2L);
        guard2.setLeft(left2);
        final Constant right2 = new Constant();
        right2.setValue(2L);
        guard2.setRight(right2);
        t3.setGuard(guard2);

        t3.setFrom(state2);
        t3.setTo(state1);
        state2.getOutgoing().add(t3);

        t1.setFsm(fsm);
        t2.setFsm(fsm);
        t3.setFsm(fsm);

        fsm.getTransitions().add(t1);
        fsm.getTransitions().add(t2);
        fsm.getTransitions().add(t3);
        fsm.setCurrentState(state1);
        return fsm;
    }
}
