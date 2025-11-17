# Adapter Design Pattern

## Overview

The Adapter Design Pattern is a structural pattern that allows incompatible interfaces to work together. It acts as a bridge between two unrelated interfaces.

---

## Intent

* Convert the interface of a class into another interface that the client expects.
* Allow otherwise incompatible classes to work together.

---

## Key Concepts

| Target                       | Adaptee                     | Adapter                              |
| ---------------------------- | --------------------------- | ------------------------------------ |
| Expected interface by client | Existing interface to adapt | Converts adaptee to target interface |
| Client works with this       | Cannot be used directly     | Implements Target, wraps Adaptee     |

---

## UML Diagram (Simplified)

```
     Client
       |
    Target Interface
       ^
       |
    Adapter ------> Adaptee
```

## When to Use

* You need to use an existing class but its interface doesn't match what you need.
* You want to reuse legacy code without modifying it.
* Third-party libraries present incompatible APIs.

---

## Types of Adapter

### 1. Class Adapter (uses inheritance)

* Uses multiple inheritance (not supported in Java)
* Adapts using subclassing

### 2. Object Adapter (uses composition) **(Most common in Java)**

* Wraps an instance of the adaptee inside the adapter

---

## Advantages

* Increases reusability of existing classes.
* Loose coupling between systems.
* Follows Single Responsibility Principle.

---

## Disadvantages

* More classes and complexity.
* Overuse can lead to unnecessary layers.

---

## Real-World Examples

* Java I/O Streams: InputStreamReader, OutputStreamWriter
* JDBC drivers adapting to database-specific implementations
* Collections API: Arrays.asList()
* Legacy integration systems

---

## Summary

* Adapter pattern converts one interface into another expected by the client.
* Works best when integrating incompatible or legacy systems.
* Uses composition in Java for cleaner and safer implementation.
