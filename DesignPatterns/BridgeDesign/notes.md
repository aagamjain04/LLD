# Bridge Design Pattern

## Overview
The Bridge pattern is a structural design pattern that decouples an abstraction from its implementation so that the two can vary independently. It uses composition over inheritance to separate the interface from the implementation.

## Intent
- Decouple abstraction from implementation
- Allow both to vary independently
- Avoid a permanent binding between abstraction and implementation
- Improve extensibility by allowing new abstractions and implementations to be added independently

## Problem it Solves
Without the Bridge pattern, when you have multiple dimensions of variation (like shapes and colors), you end up with a class explosion. For example:
- RedCircle, BlueCircle, GreenCircle
- RedSquare, BlueSquare, GreenSquare
- RedTriangle, BlueTriangle, GreenTriangle

This creates N × M classes where N is the number of shapes and M is the number of colors.

## Solution
The Bridge pattern splits these dimensions into separate hierarchies:
1. **Abstraction hierarchy**: Shape.Shape (Shape.Shape.Circle, Square, Triangle)
2. **Implementation hierarchy**: Color (Red, Blue, Green)

The Shape.Shape classes contain a reference to Color and delegate color-specific operations to it.

## Structure
```
Abstraction (Shape.Shape)
    - contains reference to Implementor (Color)
    - delegates implementation-specific work
    
RefinedAbstraction (Shape.Shape.Circle, Square)
    - extends Abstraction
    
Implementor (Color)
    - defines interface for implementation classes
    
ConcreteImplementor (Red, Blue, Green)
    - implements the Implementor interface
```

## Benefits
- Decouples interface and implementation
- Reduces the number of classes (N + M instead of N × M)
- Improves extensibility
- Hides implementation details from clients
- Allows implementation to be selected or switched at runtime

## When to Use
- You want to avoid permanent binding between abstraction and implementation
- Both abstraction and implementation should be extensible through subclassing
- Changes in implementation should not affect clients
- You want to share an implementation among multiple objects
- You have a class explosion due to multiple dimensions of variation


## Key Points
- **Composition over Inheritance**: Shape.Shape has-a Color instead of Shape.Shape is-a ColoredShape
- **Flexibility**: You can add new shapes or colors independently without affecting each other
- **Runtime Binding**: Color can be changed at runtime by passing different Color objects
- **Scalability**: Adding a new shape requires only one new class, not N classes (where N is the number of colors)

## Real-World Applications
- GUI frameworks (separating window abstraction from platform-specific implementation)
- Device drivers (abstraction for device operations, implementation for specific hardware)
- Database drivers (JDBC separates database operations from specific database implementations)
- Remote controls and electronic devices (remote abstraction, device implementation)

## Comparison with Similar Patterns

### Bridge vs Adapter
- **Bridge**: Designed upfront to let abstraction and implementation vary independently
- **Adapter**: Applied to existing systems to make incompatible interfaces work together

### Bridge vs Strategy
- **Bridge**: Separates abstraction from implementation across multiple dimensions
- **Strategy**: Focuses on making algorithms interchangeable within a single dimension

## Class Diagram
```
┌─────────────┐              ┌─────────────┐
│   Shape.Shape     │──────────────│   Color     │
│             │  has-a       │             │
├─────────────┤              ├─────────────┤
│ - color     │              │ + fill()    │
│ + draw()    │              └─────────────┘
└─────────────┘                     △
      △                             │
      │                   ┌─────────┴─────────┐
      │                   │                   │
┌─────┴─────┐      ┌──────────┐      ┌──────────┐
│  Shape.Shape.Circle   │      │   Red    │      │   Blue   │
│  Square   │      │  Green   │      │          │
│ Triangle  │      └──────────┘      └──────────┘
└───────────┘
```

## Summary
The Bridge pattern is essential when you need to vary two or more dimensions independently. It provides a flexible way to extend your system without creating a combinatorial explosion of classes. By separating "what" from "how," you can modify, extend, and reuse components more effectively.