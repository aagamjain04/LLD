# Flyweight Design Pattern - Interview Notes

## Definition
The Flyweight pattern is a structural design pattern that minimizes memory usage by sharing common data among multiple objects instead of keeping all data in each object.

## Problem It Solves
When an application needs to create a large number of similar objects, it can consume excessive memory. Flyweight reduces memory footprint by sharing intrinsic state among objects.

## Key Concepts

### Intrinsic State (Shared)
- Data that is **common** across multiple objects
- **Immutable** and stored in the flyweight object
- Example: Circle radius, font family, texture

### Extrinsic State (Unique)
- Data that is **unique** to each object
- Passed to flyweight methods by the client
- Example: position coordinates, color, context-specific data

## Components

1. **Flyweight Interface**: Defines methods that accept extrinsic state
2. **Concrete Flyweight**: Implements the interface and stores intrinsic state
3. **Flyweight Factory**: Creates and manages flyweight objects (uses pool/cache)
4. **Client**: Maintains extrinsic state and uses flyweights

## UML Structure
```
Client → FlyweightFactory → Flyweight (Interface)
                                ↑
                     ConcreteFlyweight
```

## Real-World Examples

### String Pool in Java
```java
String s1 = "hello";  // Stored in string pool
String s2 = "hello";  // Reuses same object
System.out.println(s1 == s2);  // true
```

### Other Examples
- Text editors (character objects with shared fonts)
- Game development (trees, particles, bullets with shared textures)
- Browser rendering (DOM nodes, CSS styles)
- Database connection pools
- Thread pools

## Advantages
✅ Reduces memory consumption significantly
✅ Improves performance when dealing with large numbers of objects
✅ Centralizes state management
✅ Reduces object creation overhead

## Disadvantages
❌ Adds complexity to the codebase
❌ Requires careful separation of intrinsic and extrinsic state
❌ May make code harder to understand
❌ Runtime costs of looking up/computing extrinsic state

## When to Use
- Application uses a large number of similar objects
- Storage costs are high due to quantity of objects
- Most object state can be made extrinsic
- Identity of objects doesn't matter to the application

## When NOT to Use
- Few objects are needed
- Objects have mostly unique state
- Sharing would complicate the code significantly

## Interview Questions & Answers

**Q: Difference between Flyweight and Singleton?**
A: Singleton ensures one instance per class, while Flyweight creates multiple shared instances based on intrinsic state. Flyweight uses a factory to manage multiple shared objects.

**Q: Difference between Flyweight and Object Pool?**
A: Object Pool manages reusable objects that are checked out and returned. Flyweight shares immutable objects simultaneously among many clients.

**Q: How does Flyweight improve performance?**
A: By reducing memory allocation overhead, decreasing garbage collection pressure, and enabling better CPU cache utilization through shared objects.

**Q: Is Flyweight thread-safe?**
A: The shared intrinsic state should be immutable, making it inherently thread-safe. The factory may need synchronization for thread safety during object creation.

**Q: Can you modify a flyweight object?**
A: No, flyweight objects should be immutable. All variation should come from extrinsic state passed as parameters.

## Code Interview Tips

1. **Clearly identify** intrinsic vs extrinsic state
2. **Implement factory** with caching (HashMap is common)
3. **Make flyweights immutable** (final fields)
4. **Show memory savings** in your explanation
5. **Discuss thread safety** if asked

## Comparison with Other Patterns

| Pattern | Purpose | Key Difference |
|---------|---------|---------------|
| **Singleton** | One instance per class | Flyweight has multiple shared instances |
| **Prototype** | Clone objects | Flyweight shares objects instead of cloning |
| **Object Pool** | Reuse expensive objects | Pool checks out/in; Flyweight shares simultaneously |
| **Facade** | Simplify interface | Different purpose; not about memory optimization |

## Sample Problem
**Problem**: Design a forest with 1 million trees where trees share texture/mesh data.

**Solution**:
- Intrinsic: tree type, texture, mesh (shared via flyweight)
- Extrinsic: x, y coordinates, scale (unique per tree)
- Factory manages tree type flyweights
- Result: Instead of 1M full tree objects, maybe only 10-20 flyweight types

## Key Takeaway
Flyweight is about **memory optimization through sharing**. Separate what changes (extrinsic) from what stays the same (intrinsic) and share the constant parts.