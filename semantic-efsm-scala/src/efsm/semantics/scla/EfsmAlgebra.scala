package efsm.semantics.scla

/**
  * Created by mleduc on 28/03/17.
  */
trait EfsmAlgebra[FSMT, eFSMT <: FSMT, TransitionT, StateT] {
  def fsm(fsm: syntax.efsm.FSM): eFSMT

  def fsm(fsm: syntax.fsm.FSM): FSMT

  def transition(transition: syntax.fsm.Transition): TransitionT

  def state(state: syntax.fsm.State): StateT
}
