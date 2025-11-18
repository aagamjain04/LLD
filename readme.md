# UML Relationships in Low-Level Design

## 1. Composition (Filled Diamond ♦)

**Definition**: A **strong "part-of"** relationship where the child cannot exist without the parent. When the parent is destroyed, all child objects are also destroyed.

**Visual**: Solid line with filled diamond on the parent side
```
Parent ♦——————— Child
```

**Characteristics**:
- **Lifecycle dependency**: Child's lifetime is bound to parent's lifetime
- **Ownership**: Parent owns the child completely
- **Cardinality**: Usually 1-to-many relationship
- **Memory management**: Parent is responsible for child's creation and destruction

**Real-world Example**:
```java
class House {
    private List<Room> rooms;  // Rooms cannot exist without House
    
    public House() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Living Room"));
        rooms.add(new Room("Bedroom"));
    }
    
    // When House is destroyed, all Rooms are destroyed too
}

class Room {
    private String name;
    
    public Room(String name) {
        this.name = name;
    }
}
```

**When to use in LLD**:
- Database connections and connection pools
- UI components and their child elements
- Document and its pages/sections
- Order and OrderItems in e-commerce

---

## 2. Aggregation (Empty Diamond ◊)

**Definition**: A **weak "part-of"** relationship where the child can exist independently of the parent. The parent uses the child but doesn't control its lifecycle.

**Visual**: Solid line with empty diamond on the parent side
```
Parent ◊——————— Child
```

**Characteristics**:
- **Independent lifecycle**: Child can exist without parent
- **Shared ownership**: Multiple parents can reference the same child
- **Loose coupling**: Parent and child are more independent
- **Memory management**: Child manages its own lifecycle

**Real-world Example**:
```java
class Department {
    private List<Employee> employees;  // Employees can exist without Department
    
    public Department() {
        employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee emp) {
        employees.add(emp);  // Employee exists independently
    }
}

class Employee {
    private String name;
    private int id;
    
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    // Employee can exist even if Department is destroyed
}
```

**When to use in LLD**:
- Team and team members
- Library and books
- Playlist and songs
- Course and students

---

## 3. Association

**Definition**: A **general relationship** between two classes where one class uses or interacts with another. Neither class owns the other.

**Visual**: Simple solid line, sometimes with arrows to show direction
```
Class1 ——————— Class2
Class1 ————→ Class2  (unidirectional)
Class1 ←————→ Class2  (bidirectional)
```

**Characteristics**:
- **Independent existence**: Both classes exist independently
- **Loose coupling**: Classes are related but not dependent
- **Can be bidirectional or unidirectional**
- **No ownership**: Neither class owns the other

**Real-world Example**:
```java
class Customer {
    private String name;
    private List<Order> orders;
    
    public void placeOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);  // Association
    }
}

class Order {
    private String orderNumber;
    private Customer customer;  // Association back to Customer
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
```

**When to use in LLD**:
- User and Account
- Doctor and Patient
- Teacher and Student
- Customer and Order

---

## 4. Dependency (Dashed Arrows)

**Definition**: A **weak relationship** where one class depends on another for its functionality, but doesn't store a reference to it. Usually represents method parameters, local variables, or return types.

**Visual**: Dashed line with arrow pointing to the dependency
```
Client - - - → Service
```

**Characteristics**:
- **Temporary relationship**: Dependency exists only during method execution
- **No stored reference**: Client doesn't maintain a reference to the dependency
- **Compile-time dependency**: Changes in dependency may require recompilation
- **Weakest relationship**: Easiest to break

**Real-world Example**:
```java
class OrderService {
    // PaymentProcessor is a dependency - used but not stored
    public void processOrder(Order order) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment(order.getAmount());  // Dependency usage
        
        // EmailService is also a dependency
        EmailService emailService = new EmailService();
        emailService.sendConfirmation(order.getCustomer());
    }
}

class PaymentProcessor {
    public void processPayment(double amount) {
        // Process payment logic
    }
}

class EmailService {
    public void sendConfirmation(Customer customer) {
        // Send email logic
    }
}
```

**When to use in LLD**:
- Service layer depending on utility classes
- Controllers depending on validators
- Business logic depending on external APIs
- Methods using temporary objects

---

## Summary Table

| Relationship | Symbol | Strength | Lifecycle | Ownership | Usage |
|--------------|--------|----------|-----------|-----------|-------|
| **Composition** | ♦——— | Strongest | Bound | Parent owns child | Part-of with lifecycle dependency |
| **Aggregation** | ◊——— | Strong | Independent | Shared | Part-of without lifecycle dependency |
| **Association** | ——— | Medium | Independent | No ownership | Uses or knows about |
| **Dependency** | - - -→ | Weakest | Temporary | No ownership | Temporary usage in methods |

## Best Practices in LLD

1. **Start with weakest relationship**: Use dependency when possible, escalate only when needed
2. **Favor composition over inheritance**: Composition provides better flexibility
3. **Use aggregation for shared resources**: When multiple objects need to use the same resource
4. **Document cardinality**: Specify 1-to-1, 1-to-many, many-to-many relationships
5. **Consider memory management**: Choose relationships based on object lifecycle requirements

## Common Mistakes to Avoid

- **Overusing composition**: Not everything needs strong coupling
- **Confusing aggregation with association**: Aggregation implies part-of relationship
- **Ignoring dependency direction**: Dependencies should point toward stable abstractions
- **Creating circular dependencies**: Can cause memory leaks and initialization issues

---


# Low Level Design (LLD) Interview Approach Guide

## Overview
Low Level Design focuses on detailed system architecture, class design, and implementation details. Unlike High Level Design (HLD), LLD dives into the actual code structure, APIs, and database schemas.

## Step-by-Step Approach

### 1. Clarify Requirements (5-10 minutes)
- **Functional Requirements**: What features need to be implemented?
- **Non-functional Requirements**: Performance, scalability, security constraints
- **Scope**: What's in scope vs out of scope for this design?
- **Constraints**: Time limits, technology stack, existing systems

**Key Questions to Ask:**
- What are the core features we need to support?
- What's the expected scale (users, requests per second)?
- Are there any specific technology constraints?
- What are the performance requirements?

### 2. Estimate Scale and Constraints (2-3 minutes)
- Number of users (daily/monthly active users)
- Read vs Write ratio
- Data storage requirements
- Response time expectations
- Availability requirements

### 3. Define Core Entities and Use Cases (10-15 minutes)
- Identify main entities/objects
- Define relationships between entities
- List primary use cases/user flows
- Prioritize must-have vs nice-to-have features

**Example for Chat System:**
```
Entities: User, Message, ChatRoom, Group
Use Cases: Send message, Create group, Join group, Get message history
```

### 4. Design Database Schema (10-15 minutes)
- Choose appropriate database type (SQL/NoSQL)
- Design tables/collections with proper relationships
- Define indexes for query optimization
- Consider data partitioning if needed

**Key Considerations:**
- Normalization vs denormalization trade-offs
- Primary keys and foreign key relationships
- Indexing strategy for common queries
- Data types and constraints

### 5. Design APIs (15-20 minutes)
- Define RESTful endpoints or GraphQL schemas
- Specify request/response formats
- Include authentication and authorization
- Error handling and status codes

**API Design Template:**
```
POST /api/v1/messages
Request: { "chatId": "123", "content": "Hello", "userId": "456" }
Response: { "messageId": "789", "timestamp": "2024-01-01T00:00:00Z", "status": "sent" }
```

### 6. Class Design and Architecture (15-20 minutes)
- Apply SOLID principles
- Use appropriate design patterns
- Define interfaces and abstract classes
- Show inheritance and composition relationships

**Common Design Patterns:**
- **Factory Pattern**: Object creation
- **Observer Pattern**: Event notifications
- **Strategy Pattern**: Algorithm selection
- **Command Pattern**: Request encapsulation
- **Decorator Pattern**: Feature enhancement

### 7. Handle Edge Cases and Error Scenarios (5-10 minutes)
- Network failures and timeouts
- Data validation and sanitization
- Concurrent access issues
- Rate limiting and abuse prevention
- Graceful degradation

## Key Principles to Follow

### SOLID Principles
- **S**ingle Responsibility: One class, one purpose
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable
- **I**nterface Segregation: Many specific interfaces > one general
- **D**ependency Inversion: Depend on abstractions, not concretions

### Design Patterns Checklist
- **Creational**: Factory, Builder, Singleton
- **Structural**: Adapter, Decorator, Facade
- **Behavioral**: Observer, Strategy, Command, State
