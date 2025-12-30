# Mediator Design Pattern

## Overview
The Mediator pattern is a behavioral design pattern that reduces chaotic dependencies between objects by restricting direct communications between them and forcing them to collaborate only via a mediator object.

## Intent
- Define an object that encapsulates how a set of objects interact
- Promote loose coupling by keeping objects from referring to each other explicitly
- Let you vary the interaction independently

## Problem It Solves
When you have many objects that need to communicate with each other, direct connections create a tangled web of dependencies. Each object needs to know about many other objects, making the system hard to maintain and modify.

## Key Components

### 1. **Mediator (Interface/Abstract Class)**
Defines the interface for communication between colleague objects.

### 2. **Concrete Mediator**
Implements the mediator interface and coordinates communication between colleague objects. Knows and maintains references to all colleagues.

### 3. **Colleague Classes**
Objects that communicate with each other through the mediator instead of directly. Each colleague has a reference to the mediator.

## Structure
```
Colleague A -----> Mediator <----- Colleague B
                      ^
                      |
Colleague C ----------+
```

## Advantages
- **Loose Coupling**: Objects don't need to know about each other's existence
- **Centralized Control**: Interaction logic is in one place
- **Simplified Communication**: Reduces complex many-to-many relationships to manageable one-to-many
- **Easier Maintenance**: Changes to interaction logic only affect the mediator
- **Reusability**: Colleagues can be reused more easily

## Disadvantages
- **God Object**: Mediator can become overly complex
- **Single Point of Failure**: If mediator fails, all communication fails
- **Performance**: Additional layer of indirection

## When to Use
- When objects communicate in complex but well-defined ways
- When reusing objects is difficult due to many dependencies
- When behavior distributed among classes should be customizable without subclassing
- When you want to centralize complex communications and control

## Real-World Examples
- Air traffic control system (planes don't talk to each other directly)
- Chat room applications (users communicate through chat room)
- GUI frameworks (form controls communicate through a dialog/form)
- Event dispatchers and message brokers

---



## Comparison with Other Patterns

### Mediator vs Observer
- **Observer**: One-to-many broadcast, observers are independent
- **Mediator**: Centralized control, colleagues interact through mediator

### Mediator vs Facade
- **Facade**: Simplifies interface to subsystem, unidirectional
- **Mediator**: Coordinates behavior between objects, bidirectional

### Mediator vs Chain of Responsibility
- **Chain**: Request passes along chain until handled
- **Mediator**: Central coordinator distributes to appropriate objects

## Best Practices
1. Keep mediator interface simple and focused
2. Don't let mediator become a "god object"
3. Consider splitting complex mediators into smaller ones
4. Use mediator when communication patterns are well-defined
5. Document the interaction protocols in the mediator

## Summary
The Mediator pattern promotes loose coupling by centralizing complex communications between objects. While it can simplify object interactions and make the system easier to maintain, care must be taken to prevent the mediator itself from becoming too complex.