import b.FSM;
import b.State;
import b.Step;
import b.Transition;

import java.util.*;
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

        final Map<String, String> conversionAC = new HashMap<>();
        conversionAC.put("a.FSM", "c.FSM");
        conversionAC.put("a.State", "c.State");
        conversionAC.put("a.Transition", "c.Transition");
        conversionAC.put("c.FSM", "a.FSM");
        conversionAC.put("c.State", "a.State");
        conversionAC.put("c.Transition", "a.Transition");

        final Map<String, String> implToIntfAC = new HashMap<>();
        implToIntfAC.put("a.impl.FSMImpl", "a.FSM");
        implToIntfAC.put("a.impl.StateImpl", "a.State");
        implToIntfAC.put("a.impl.TransitionImpl", "a.Transition");
        implToIntfAC.put("c.impl.FSMImpl", "c.FSM");
        implToIntfAC.put("c.impl.StateImpl", "c.State");
        implToIntfAC.put("c.impl.TransitionImpl", "c.Transition");

        final Map<String, String> conversionBC = new HashMap<>();
        conversionBC.put("b.FSM", "c.FSM");
        conversionBC.put("b.State", "c.State");
        conversionBC.put("b.Transition", "c.Transition");
        conversionBC.put("b.Step", "c.Step");
        conversionBC.put("c.FSM", "b.FSM");
        conversionBC.put("c.State", "b.State");
        conversionBC.put("c.Transition", "b.Transition");
        conversionBC.put("c.Step", "b.Step");


        final Map<String, String> implToIntfBC = new HashMap<>();
        implToIntfBC.put("b.impl.FSMImpl", "b.FSM");
        implToIntfBC.put("b.impl.StateImpl", "b.State");
        implToIntfBC.put("b.impl.TransitionImpl", "b.Transition");
        implToIntfBC.put("b.impl.StepImpl", "b.Step");
        implToIntfBC.put("c.impl.FSMImpl", "c.FSM");
        implToIntfBC.put("c.impl.StateImpl", "c.State");
        implToIntfBC.put("c.impl.TransitionImpl", "c.Transition");
        implToIntfBC.put("c.impl.StepImpl", "c.Step");

        final c.FSM fsm = new c.impl.FSMImpl();
        final c.impl.StateImpl e = new c.impl.StateImpl();
        e.setName("opened");
        fsm.getStates().add(e);

        final c.impl.StateImpl f = new c.impl.StateImpl();
        f.setName("closed");
        fsm.getStates().add(f);

        final c.impl.TransitionImpl t1 = new c.impl.TransitionImpl();
        t1.setFrom(e);
        t1.setTo(f);
        t1.setEvent("close");
        e.getOutgoing().add(t1);

        final c.impl.TransitionImpl t2 = new c.impl.TransitionImpl();
        t2.setFrom(f);
        t2.setTo(e);
        t2.setEvent("open");
        f.getOutgoing().add(t2);


        Program program = new Program();
        program.prettyPrint(new GenericProxyFactory(conversionAC, implToIntfAC).getFlipFlop(a.FSM.class, fsm));
        ArrayList<String> events = new ArrayList<>();
        events.add("closec");
        events.add("openc");
        events.add("closec");
        GenericProxyFactory genericProxyFactory = new GenericProxyFactory(conversionBC, implToIntfBC);
        program.execute(genericProxyFactory.getFlipFlop(b.FSM.class, fsm), events, genericProxyFactory);

        System.out.println("---------");

        c.FSM fsm2 = new UndioProxyFactory().reroll(c.FSM.class, fsm);
        List<c.Step> steps = fsm2.getSteps();
        for (c.Step step : steps) {
            System.out.println(step);
            System.out.println(step.getState());
            System.out.println(step.getState().getName());
        }
        System.out.println("---------");
    }

    private void prettyPrint(a.FSM fsm) {
        for (final a.State s : fsm.getStates()) {
            System.out.println("# State " + s.getName());
            for (final a.Transition t : s.getOutgoing()) {
                System.out.println("- " + t.getEvent() + " => " + t.getTo().getName());
            }
        }
    }

    private void execute(FSM fsm, List<String> events, GenericProxyFactory genericProxyFactory) {
        if (fsm.getCurrentState() == null) {
            final List<State> states = fsm.getStates();
            final State state = states.get(0);
            fsm.setCurrentState(state);
        }

        for (String event : events) {
            final List<b.Transition> strm = fsm.getCurrentState().getOutgoing().stream().filter(t -> Objects.equals(t.getEvent(), event)).collect(Collectors.toList());
            if (strm.size() == 0) {
                System.out.println("DEADLOCK");
                break;
            } else if (strm.size() > 1) {
                System.out.println("UNCERTAINTY");
                break;
            } else {
                final b.Transition t = strm.get(0);
                final State to = t.getTo();
                fsm.setCurrentState(to);
                b.Step e = genericProxyFactory.getFlipFlop(b.Step.class, new c.impl.StepImpl());
                e.setState(t.getFrom());
                final List<Step> steps = fsm.getSteps();
                steps.add(e);
            }
        }

        final List<Step> steps = fsm.getSteps();
        for (Step s : steps) {
            System.out.println(s);
            System.out.println(s.getState());
            System.out.println(s.getState().getName());
        }
    }
}
