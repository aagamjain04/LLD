# Observer Design Pattern in Java

## Overview

The Observer Design Pattern is a behavioral design pattern that defines a one-to-many dependency between objects. When one object (the subject) changes its state, all its dependents (observers) are notified and updated automatically.

## Key Components

1. **Subject (Observable)**:
    - Maintains a list of observers
    - Provides methods to register and unregister observers
    - Sends notifications to observers when its state changes

2. **Observer**:
    - Defines an interface for receiving updates from the subject
    - Implements a method to be called when the subject's state changes

## Benefits

- **Loose Coupling**: Subject and observers are loosely coupled
- **Open/Closed Principle**: Easy to add new observers without modifying the subject
- **Dynamic Relationships**: Observers can be added or removed at runtime
- **Broadcast Communication**: One subject can notify multiple observers
- **Promotes Reusability**: Observers can be reused independently

