package efsm.semantics.scla

/**
  * Created by mleduc on 29/03/17.
  */
object Machin extends App {

  class C {
    def size(offset: Int) = 42L + offset;
  }

  println(new TestThing().myMethod(new C))
}
