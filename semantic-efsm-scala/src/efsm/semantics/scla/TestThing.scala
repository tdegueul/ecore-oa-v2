package efsm.semantics.scla

/**
  * Created by mleduc on 29/03/17.
  */
class TestThing {
  def myMethod(param: {def size(offset: Int): Long}): Long = {
    val a = 42
    val b = 1
    val c = param.size(a)
    c + b
  }

  def secondMethod(param: (Long) => Long) = {

    val a = 42
    val b = 1
    val c = param(a)
    c + b
  }

  def thirdMethod = {
    val s = "foo"
    (x: String) => x == s
  }
}

