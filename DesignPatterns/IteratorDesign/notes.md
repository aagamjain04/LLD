# Iterator Design Pattern

## Definition
The **Iterator Design Pattern** provides a way to **access elements of a collection sequentially without exposing its underlying representation**.

> It separates the traversal logic from the collection itself.

---

## Problem It Solves
- Different collections (Array, List, Set, Tree, etc.) have **different internal structures**
- Client code should not depend on **how data is stored**
- Traversal logic mixed inside collection violates **Single Responsibility Principle**

---

## Solution
- Define a common **Iterator interface**
- Each collection provides its own iterator implementation
- Client uses iterator methods instead of accessing internal structure

---

## UML Participants
1. **Iterator**
    - Declares traversal operations (`hasNext()`, `next()`)

2. **ConcreteIterator**
    - Implements Iterator
    - Keeps track of current position

3. **Aggregate (Collection)**
    - Declares method to create iterator

4. **ConcreteAggregate**
    - Implements iterator creation

---

## Simple Real-World Analogy
- **TV Remote**
    - You don't know how channels are stored
    - You just press `next` / `previous`

---

## Java Example (Custom Implementation)

### 1️⃣ Iterator Interface
```java
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
