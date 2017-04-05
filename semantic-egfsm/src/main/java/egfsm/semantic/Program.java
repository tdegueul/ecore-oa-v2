package egfsm.semantic;

import egfsm.semantic.impl.ExecutableEGFSMAlgebra;
import egfsm.semantic.operation.ExecutableFSMOperation;
import syntax.efsm.FSM;
import syntax.fsm.State;
import syntax.gfsm.Transition;

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

        t1.setGuard(true);


        t1.setFrom(state1);
        t1.setTo(state2);
        state1.getOutgoing().add(t1);

        final Transition t2 = new Transition();
        t2.setEvent("b");

        t2.setGuard(true);

        t2.setFrom(state2);
        t2.setTo(state1);
        state2.getOutgoing().add(t2);


        final Transition t3 = new Transition();
        t3.setEvent("b");
        t3.setGuard(false);

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
