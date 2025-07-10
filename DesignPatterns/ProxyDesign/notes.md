# Proxy Design Pattern in Java

## Overview

The Proxy Design Pattern is a structural design pattern that provides a placeholder or surrogate for another object to control access to it. The proxy acts as an intermediary between the client and the real object, allowing you to perform additional operations before or after forwarding requests to the real object.

## Key Concepts

### What is a Proxy?
- A proxy is a wrapper or agent object that controls access to another object
- It implements the same interface as the real object
- Clients interact with the proxy instead of the real object directly
- The proxy can add functionality like lazy loading, access control, caching, or logging

### Structure
```
Client → Proxy → RealSubject
```

## UML Structure

```
<<interface>>
Subject
+operation()
    ↑
    |
    |---- RealSubject
    |     +operation()
    |
    |---- Proxy
          -realSubject: RealSubject
          +operation()
```

## Types of Proxies

### 1. Virtual Proxy
- **Purpose**: Lazy initialization of expensive objects
- **Use Case**: Loading large images, database connections
- **Benefit**: Improves performance by delaying object creation

### 2. Protection Proxy
- **Purpose**: Controls access to the original object
- **Use Case**: User authentication, permission checking
- **Benefit**: Adds security layer

### 3. Remote Proxy
- **Purpose**: Represents an object in different address space
- **Use Case**: Web services, RMI objects
- **Benefit**: Handles network communication complexity

### 4. Smart Proxy
- **Purpose**: Adds additional behavior when accessing objects
- **Use Case**: Reference counting, caching, logging
- **Benefit**: Provides enhanced functionality

