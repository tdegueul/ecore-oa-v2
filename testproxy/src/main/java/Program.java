import b.Step;
import b.impl.StateImpl;
import b.impl.StepImpl;
import b.impl.TransitionImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 29/03/17.
 */
public class Program {
   /* public static void main(String[] args) {
        final A res = GenericProxyFactory.getProxy(A.class, new AImpl());
        new Program().execa(res);
    }

    private void execa(A a) {
        System.out.println(a.getName());
    }*/

    public static void main(String[] args) {
        final Map<String, String> conversion = new HashMap<>();
        conversion.put("a.FSM", "b.FSM");
        conversion.put("a.State", "b.State");
        conversion.put("a.Transition", "b.Transition");
        conversion.put("b.FSM", "a.FSM");
        conversion.put("b.State", "a.State");
        conversion.put("b.Transition", "a.Transition");

        final Map<String, String> implToIntf = new HashMap<>();
        implToIntf.put("a.impl.FSMImpl", "a.FSM");
        implToIntf.put("a.impl.StateImpl", "a.State");
        implToIntf.put("a.impl.TransitionImpl", "a.Transition");
        implToIntf.put("b.impl.FSMImpl", "b.FSM");
        implToIntf.put("b.impl.StateImpl", "b.State");
        implToIntf.put("b.impl.TransitionImpl", "b.Transition");

        final a.FSM fsm = new a.impl.FSMImpl();
        final a.impl.StateImpl e = new a.impl.StateImpl();
        e.setName("opened");
        fsm.getStates().add(e);

        final a.impl.StateImpl f = new a.impl.StateImpl();
        f.setName("closed");
        fsm.getStates().add(f);

        final a.impl.TransitionImpl t1 = new a.impl.TransitionImpl();
        t1.setFrom(e);
        t1.setTo(f);
        t1.setEvent("close");
        e.getOutgoing().add(t1);

        final a.impl.TransitionImpl t2 = new a.impl.TransitionImpl();
        t2.setFrom(f);
        t2.setTo(e);
        t2.setEvent("open");
        f.getOutgoing().add(t2);


        Program program = new Program();
        //program.prettyPrint(new GenericProxyFactory(conversion, implToIntf).getFlipFlop(a.FSM.class, fsm));
        ArrayList<String> events = new ArrayList<>();
        events.add("close");
        events.add("open");
        events.add("close");
        program.execute(new GenericProxyFactory(conversion, implToIntf).getFlipFlop(b.FSM.class, fsm), events);
    }

    private void prettyPrint(a.FSM fsm) {
        for (final a.State s : fsm.getStates()) {
            System.out.println("# State " + s.getName());
            for (final a.Transition t : s.getOutgoing()) {
                System.out.println("- " + t.getEvent() + " => " + t.getTo().getName());
            }
        }
    }

    private void execute(b.FSM fsm, List<String> events) {
        if (fsm.getCurrentState() == null) {
            fsm.setCurrentState(fsm.getStates().get(0));
        }

        for (String event : events) {
            final List<b.Transition> strm = fsm.getCurrentState().getOutgoing().stream().filter(t -> t.getEvent().equals(event)).collect(Collectors.toList());
            if (strm.size() == 0) {
                System.out.println("DEALOCK");
                break;
            } else if (strm.size() > 1) {
                System.out.println("UNCERTAINTY");
                break;
            } else {
                final b.Transition t = strm.get(0);
                fsm.setCurrentState(t.getTo());
                StepImpl e = new StepImpl();
                e.setState(t.getFrom());
                fsm.getSteps().add(e);
            }
        }

        for (Step s : fsm.getSteps()) {
            System.out.println(s.getState());
        }
    }
}
