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