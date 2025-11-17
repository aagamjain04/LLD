# Composite Design Pattern

## Overview

The Composite Design Pattern is a structural design pattern used to treat a group of objects in the same way as a single instance of an object. It allows you to build tree-like hierarchical structures where individual objects (leaf nodes) and groups of objects (composite nodes) are treated uniformly.

---

## Intent

* Represent part-whole hierarchies.
* Allow clients to work with individual objects and compositions of objects uniformly.

---

## Key Concepts

| Component                      | Leaf                          | Composite                        |
| ------------------------------ | ----------------------------- | -------------------------------- |
| Declares interface for objects | Represents individual objects | Represents a group of components |
| May implement default behavior | Has no children               | Can contain children             |
| Common operations apply to all | Performs actual work          | Delegates work to children       |

---

## UML Diagram (Simplified)

```
        Component
       /         \
    Leaf      Composite
```

---


## When to Use

* When you want to represent part-whole hierarchies.
* When clients should treat simple and complex objects uniformly.
* When tree-like structure is needed.

---

## Advantages

* Simplifies client code.
* Makes adding new components easier.
* Supports nested hierarchical structures.

---

## Disadvantages

* Harder to enforce type constraints.
* Can become overly general.

---

## Real-World Examples

* GUI frameworks (Button, Panel, Window)
* File systems (Files & Folders)
* JSON/XML structures
* Organization hierarchies (Employee, Manager)

---

## Summary

* Composite Pattern lets you build trees of objects.
* Treats individual objects and groups of objects the same.
* Best suited for hierarchical structures.
