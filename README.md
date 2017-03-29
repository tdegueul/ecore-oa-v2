# Object Algebra (v2)

## Project structure

- **syntax-\*:** syntactic definitions
  - **fsm:** a simple fsm with fsm/state/transition
  - **efsm <: fsm:** an executable fsm with a current statm
  - **gfsm <: fsm:** a fsm with a boolean guard on transitions
  - **egfsm <: efsm with gfsm:** an executable guarded fsm with the notion and current state and the notion of guards on transitions.
- **semantic-\*:** semantic definitions
  - **efsm:** The semantic of the executable fsm
  - **egfsm <: efsm** The semantic of the executable fsm with guards on transitions (it only redefines what needs to be redefined)



# Interesting bits

## Pattern

### General

When creating a *concrete algebra* an **Operation** interface is defined for each abstract type in the *abstract algebra*.

The Operation implToIntf are defined for each non `$` methods declared in the abstract algebra ([link to an example](https://github.com/manuelleduc/ecore-oa-v2/blob/master/semantic-efsm/src/main/java/efsm/semantic/impl/ExecutableEFSMAlgebra.java#L22)).

When an implementation of a part of the semantic has to access to an operation or an attribute which might not be defined for some elements ([link to an example](https://github.com/manuelleduc/ecore-oa-v2/blob/master/semantic-efsm/src/main/java/efsm/semantic/impl/ExecutableEFSMAlgebra.java#L104)) an operation has to be added to the Operation interface of the manipulated model's element.

### Reuse and inheritance

#### "Self" context propagation

(Inspired from "Modular interpreter for the Masses - Implicit Context Propagation using Object Algebra")

A concrete implementation expect two parameters. 

The first on is an instance of the element type manipulated.

The second is an instance of an algebra with acceptable abstract types ([link to an example](https://github.com/manuelleduc/ecore-oa-v2/blob/master/semantic-egfsm/src/main/java/egfsm/semantic/impl/ExecutableEGFSMAlgebra.java#L49)).

**Note: ** The use of `? extends XXXOperation` allow us to pass the concrete Algebra instantiated at runtime up to the whole inherited hierarchy. Doing so, every time an element is passed to a `$` method, it evaluation is dispatched from the bottom.

#### Multi-inheritance reuse

In order to simulate the behavior of a multiple inheritance in java, the [delegation pattern](en.wikipedia.org/wiki/Delegation_pattern) is used ([link to an example](https://github.com/manuelleduc/ecore-oa-v2/blob/master/semantic-egfsm/src/main/java/egfsm/semantic/impl/ExecutableEGFSMAlgebra.java#L98)). The delegate is then used to fill every not overloaded methods of an **Operation** interface's method.

**Note:** This is only ok in the context of a code generated code base but allows us to be type-safe at every level using on "in the small" inheritance for each direction's extensions.