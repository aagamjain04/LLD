# Command Design Pattern - Interview Notes

## Definition
The Command pattern encapsulates a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

## Intent
Decouple the object that invokes an operation from the one that knows how to perform it.

## Problem It Solves
- Need to parameterize objects by an action to perform
- Need to specify, queue, and execute requests at different times
- Need to support undo/redo operations
- Need to structure a system around high-level operations built on primitive operations
- Need to support logging changes so they can be reapplied after a system crash

## Key Components

### 1. Command (Interface)
- Declares an interface for executing an operation
- Typically has `execute()` method
- Often includes `undo()` for reversible operations

### 2. ConcreteCommand
- Implements the Command interface
- Defines binding between a Receiver and an action
- Implements `execute()` by invoking corresponding operations on Receiver
- Stores state for undo operations

### 3. Receiver
- The object that actually performs the work
- Knows how to carry out the request
- Any class can serve as a Receiver

### 4. Invoker
- Asks the command to carry out the request
- Doesn't know anything about concrete commands or receivers
- Holds a command and calls its `execute()` method

### 5. Client
- Creates ConcreteCommand objects
- Sets the Receiver of the command
- Configures the Invoker with commands

## UML Structure
```
Client → Command (interface)
         ↓
         ConcreteCommand → Receiver
         ↓
Invoker → Command.execute()
```

## Real-World Analogies

### Restaurant Ordering System
- **Client**: Customer
- **Invoker**: Waiter
- **Command**: Order slip
- **Receiver**: Cook
- **execute()**: Cook prepares the meal

### Remote Control
- **Client**: User
- **Invoker**: Remote Control
- **Command**: Button press actions
- **Receiver**: TV, Fan, Light, etc.
- **execute()**: Turn on/off, change channel, etc.

## Advantages

1. **Decoupling**: Separates the object invoking the operation from the one performing it
2. **Extensibility**: Easy to add new commands without changing existing code (Open/Closed Principle)
3. **Undo/Redo**: Commands can store state for reversible operations
4. **Macro Commands**: Can combine multiple commands into one
5. **Queueing**: Commands can be queued for delayed execution
6. **Logging**: Command history can be logged for audit or replay
7. **Transactional Behavior**: Can implement rollback mechanisms

## Disadvantages

1. **Complexity**: Increases number of classes in the system
2. **Memory Overhead**: Each command is a separate object
3. **Maintenance**: More classes to maintain and test

## Common Interview Questions & Answers

### Q1: What's the difference between Command and Strategy pattern?
**Answer**: Both encapsulate algorithms/actions, but:
- **Command**: Encapsulates a request as an object, focuses on decoupling invoker from receiver, supports undo/redo
- **Strategy**: Encapsulates interchangeable algorithms, focuses on selecting algorithm at runtime, no undo support

### Q2: How do you implement undo functionality?
**Answer**:
- Store previous state in the command before executing
- Implement `undo()` method that restores the previous state
- Maintain a command history stack in the Invoker
- Example: Store fan's previous speed before changing it

### Q3: What is a Macro Command?
**Answer**: A command that executes multiple commands in sequence. It's a Composite pattern applied to commands, useful for "party mode" scenarios where multiple devices need coordinated control.

### Q4: When would you use the Command pattern?
**Answer**:
- GUI buttons and menu items
- Transaction systems requiring rollback
- Task scheduling and queueing
- Macro recording and playback
- Multi-level undo/redo
- Wizard-style interfaces

### Q5: How does Command pattern support Open/Closed Principle?
**Answer**: New commands can be added without modifying existing Invoker or Receiver classes. The Invoker works with the Command interface, so it's open for extension (new commands) but closed for modification.

### Q6: What is the Null Object pattern in Command?
**Answer**: A NoCommand class that implements Command interface but does nothing. Used to initialize slots in remote control, avoiding null checks. Follows Null Object pattern to eliminate special-case handling.

### Q7: Can you explain command queuing?
**Answer**: Commands can be stored in a queue and executed sequentially or in parallel. Useful for:
- Thread pools
- Job scheduling systems
- Event-driven architectures
- Background task processing

### Q8: How would you implement a command history?
**Answer**:
```java
public class RemoteControl {
    private Stack<Command> history = new Stack<>();

    public void pressButton(Command command) {
        command.execute();
        history.push(command);
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
}
```

## Real-World Use Cases

1. **Text Editors**: Undo/Redo operations (Ctrl+Z, Ctrl+Y)
2. **Database Transactions**: Commit/Rollback
3. **GUI Frameworks**: Button clicks, menu selections (Swing Actions)
4. **Game Development**: Replay systems, turn-based games
5. **Task Schedulers**: Cron jobs, batch processing
6. **Networking**: Request-response protocols
7. **Smart Home Systems**: Device control automation

## Code Variations

### Simple Command (No Undo)
```java
interface Command {
    void execute();
}
```

### Command with Parameters
```java
interface Command<T> {
    void execute(T parameter);
}
```

### Asynchronous Command
```java
interface Command {
    CompletableFuture<Void> executeAsync();
}
```

## Related Patterns

- **Chain of Responsibility**: Commands can be chained
- **Composite**: Macro commands are composite commands
- **Memento**: Used with Command to implement undo (stores state)
- **Prototype**: Commands can be cloned
- **Strategy**: Similar structure, different intent

## Tips for Interviews

1. Start with a simple example (Light on/off)
2. Show understanding of all five components
3. Explain undo mechanism clearly
4. Mention real-world applications
5. Discuss trade-offs (complexity vs. flexibility)
6. Be ready to code on whiteboard or in IDE
7. Explain how it follows SOLID principles

## Quick Summary
The Command pattern turns requests into standalone objects containing all information about the request. This transformation lets you pass requests as method arguments, delay or queue execution, and support undoable operations. It decouples the sender from the receiver, making the system more flexible and maintainable.