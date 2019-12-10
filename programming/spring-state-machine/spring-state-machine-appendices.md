# Spring State Machine Appendices

## Appendices A: Support Content 支撑材料

此附录提供了关于本参考文档中使用到的 Java 类和相关材料的信息。

### Classes Used in This Docunment 文档中用到的类

下面列出了本参考文档中出现的类：

```java
public enum States {
  SI,S1,S2,S3,S4,SF
}
```

```java
public enum States2 {
  S1,S2,S3,S4,S5,SF,
  S2I,S21,S22,S2F,
  S3I,S31,S32,S3F
}
```

```java
public enum States3 {
  S1,S2,SH,
  S2I,S21,S22,S2F
}
```

```java
public enum Events {
  E1,E2,E3,E4,EF
}
```

## Appendices B: State Machine Concepts 状态机概念

此附录提供了关于状态机的一些通用信息。

### Quick Example 快速上手实例

假设我们有 `STATE1` 和 `STATE2` 两个状态和 `EVENT1` 和 `EVENT2` 两个事件，状态机的逻辑可以按下图所示来定义：

![statechart0.png](./resources/statechart0.png)

下面的代码展示了上图定义的状态机：

```java
public enum States {
  STATE1, STATE2
}

public enum Events {
  EVENT1, EVENT2
}
```

```java
@Configuration
@EnableStateMachine
public class Config1 extends EnumStateMachineConfigurerAdapter<States, Events> {

  @Override
  public void configure(StateMachineStateConfigurer<States, Events> states)
      throws Exception {
    states
        .withStates()
        .initial(States.STATE1)
        .states(EnumSet.allOf(States.class));
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
      throws Exception {
    transitions
        .withExternal()
        .source(States.STATE1).target(States.STATE2)
        .event(Events.EVENT1)
        .and()
        .withExternal()
        .source(States.STATE2).target(States.STATE1)
        .event(Events.EVENT2);
  }
}
```

```java
@WithStateMachine
public class MyBean {

  @OnTransition(target = "STATE1")
  void toState1() {
  }

  @OnTransition(target = "STATE2")
  void toState2() {
  }
}
```

```java
public class MyApp {

  @Autowired
  StateMachine<States, Events> stateMachine;

  void doSignals() {
    stateMachine.sendEvent(Events.EVENT1);
    stateMachine.sendEvent(Events.EVENT2);
  }
}
```

### Glossary 术语表

#### State Machine 状态机

- The main entity that drives a collection of states, together with regions, transitions, and events.
- 使用 regions，transitions，events 驱动 states 的主要实体

#### State

- A state models a situation during which some invariant condition holds. The state is the main entity of a state machine where state changes are driven by events.
- State 是对某些不变条件保持不变的情况进行建模。状态是状态机的主要实体，其中状态更改由事件驱动

#### Extended State

- An extended state is a special set of variables kept in a state machine to reduce the number of needed states.
- Extended state 是为了减少状态的数量而存储在状态机中的一组变量。

#### Transition

- A transition is a relationship between a source state and a target state. It may be part of a compound transition, which takes the state machine from one state configuration to another, representing the complete response of the state machine to an occurrence of an event of a particular type.
- Transition 是源状态和目标状态之间的关系。它可能是一个复合转换的一部分，参与将状态机从一个 State Configuration转换为另一个的过程，表示状态机对某一特定事件的完整反应。

#### Event

- An entity that is sent to a state machine and then drives a various state changes.
- Event 是被用于传递给状态机并触发状态转换的实体。

#### Initial State

- A special state in which the state machine starts. The initial state is always bound to a particular state machine or a region. A state machine with multiple regions may have a multiple initial states.
- Initial State 是状态机的初始状态。初始状态始终与一个特定的状态机或 Region 绑定。与多个 Region 绑定的状态机可能会有多个 Initial State。

#### End State

- (Also called as a final state.) A special kind of state signifying that the enclosing region is completed. If the enclosing region is directly contained in a state machine and all other regions in the state machine are also completed, the entire state machine is completed.
- End State 也被称作 Final State，表示封闭的 Region 已经完成。若此封闭的 Region 直接被包含在一个状态机中且状态机中的其它 Regions 也完成了，则整个状态机就完成了。

#### History State

- A pseudo state that lets a state machine remember its last active state. Two types of history state exists: shallow (which remembers only top level state) and deep (which remembers active states in sub-machines).
- 一种使得状态机记住其最后的活动状态的伪状态。存在两种类型的历史状态：浅层（仅记住顶级状态）和深层（记住子机器中的活动状态）。

#### Choice State

- A pseudo state that allows for making a transition choice based on (for example) event headers or extended state variables.
- 允许基于（例如）事件头或者 Extended State 变量进行转换选择的一种伪状态。

#### Junction State

- A pseudo state that is relatively similar to choice state but allows multiple incoming transitions, while choice allows only one incoming transition.
- 与 Choice 状态相对类似的一种伪状态，允许传入多个 Transitions，而 Choice 只允许传入一个 Transition。

#### Fork State

- A pseudo state that gives controlled entry into a region.
- 一种为 Region 提供受控制的 Entry 的伪状态。

#### Join State

- A pseudo state that gives controlled exit from a region.
- 一种从 Region 获取受控制的 Exit 的伪状态。

#### Entry Point

- A pseudo state that allows controlled entry into a submachine.
- 一种为子状态机提供受控 Entry 的伪状态。

#### Exit Point

- A pseudo state that allows controlled exit from a submachine.
- 一种从子状态机获取受控 Exit 的伪状态。

#### Region

- A region is an orthogonal part of either a composite state or a state machine. It contains states and transitions.
- Region 是复合状态或状态机的正交部分，包含 States 和 Transitions。

#### Guard

- A boolean expression evaluated dynamically based on the value of extended state variables and event parameters. Guard conditions affect the behavior of a state machine by enabling actions or transitions only when they evaluate to TRUE and disabling them when they evaluate to FALSE.
- Guard 是一个基于 Extended State 变量值和 Event 参数动态计算的布尔表达式。Guard 条件仅在计算为 TRUE 时启用 Actions 或者 Transitions，而在计算为 FALSE 时禁用操作或者转换，从而影响状态机的行为。

#### Action

- A action is a behavior run during the triggering of the transition.
- Action 是在 Transition 被触发时运行的行为。

### A State Machine Crash Course 状态机速成课程

本附录提供了状态机概念的通用速成课程。

#### States 状态

State 是一个状态机可以成为的模型。相比于一些文档中使用的抽象概念，使用现实世界的例子来描述 State 要容易得多。因此请考虑一个关于键盘（基本大多数程序员每天都使用的物品）的简单例子。如果你拥有一个常用按键在左侧、数字小键盘在右侧的键盘，你应该会注意到，随着 numlock 的开启关闭，数字小键盘可能处于两种状态中。若数字小键盘未启用，按数字键可以使用箭头等来导航，反之则会敲击出相应的数字。本质上，键盘的数字键盘部分可以处于两种不同的状态。

将

To relate state concept to programming, it means that instead of using flags, nested if/else/break clauses, or other impractical (and sometimes tortuous) logic, you can rely on state, state variables, or another interaction with a state machine.