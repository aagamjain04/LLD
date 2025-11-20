# Builder Design Pattern

## Overview
The Builder Design Pattern is a creational design pattern that separates the construction of a complex object from its representation. It allows you to create different representations of an object using the same construction process.

## Purpose
- To construct complex objects step by step
- To produce different types and representations of an object using the same construction code
- To avoid telescoping constructor anti-pattern (constructor with too many parameters)

## When to Use
1. Too Many Constructor Parameters: When a class has many optional parameters (more than 4-5)
2. Immutable Objects: When you want to create immutable objects with many fields
3. Complex Object Creation: When object creation involves multiple steps
4. Different Representations: When you need to create different representations of the same object
5. Readability: When you want to make object construction more readable and maintainable

## Structure

```
Product (e.g., Computer)
    └── Contains all the properties
    └── Private constructor
    └── Static nested Builder class
        └── Same properties as Product
        └── Constructor with required parameters
        └── Setter methods returning 'this' (for method chaining)
        └── build() method returning Product
```

## Advantages

1. Clearer Code: More readable than constructors with many parameters
2. Immutability: Helps create immutable objects
3. Flexibility: Easy to add new optional parameters without breaking existing code
4. Method Chaining: Fluent API makes code more readable
5. Controlled Construction: You can enforce construction rules in the build() method
6. Single Responsibility: Separates construction logic from business logic

## Disadvantages

1. Code Duplication: Builder class duplicates the fields of the product class
2. Increased Complexity: More classes and code to maintain
3. Memory Overhead: Additional object (Builder) is created
4. Overkill for Simple Objects: Not necessary for objects with few parameters

## Key Components

### 1. Product Class
- The complex object being built
- Has a private constructor
- Contains all the properties

### 2. Builder Class (Static Nested)
- Contains the same properties as the Product
- Constructor accepts required parameters
- Setter methods for optional parameters
- Each setter returns `this` for method chaining
- `build()` method creates and returns the Product

## Code Examples

### Example: Computer Builder

```java
// Creating a gaming computer
Computer gamingPC = new Computer.ComputerBuilder("Intel i9", "32GB")
    .setStorage("2TB SSD")
    .setGraphicsCard("RTX 4090")
    .setWifiEnabled(true)
    .build();

// Creating a basic computer
Computer basicPC = new Computer.ComputerBuilder("Intel i5", "8GB")
    .build();
```

## Builder vs Other Patterns

### Builder vs Factory Pattern
- Factory: Creates objects in one step
- Builder: Creates objects step by step with method chaining

### Builder vs Abstract Factory
- Abstract Factory: Creates families of related objects
- Builder: Focuses on constructing a single complex object step by step

### Builder vs Prototype
- Prototype: Creates objects by cloning
- Builder: Creates objects by specifying configuration

## Implementation Approaches

### 1. Classic Builder (Separate Class)
```java
public class ProductBuilder {
    // Separate builder class
}
```

### 2. Inner Static Builder (Recommended)
```java
public class Product {
    public static class Builder {
        // Builder as static nested class
    }
}
```

### 3. Fluent Interface Builder
```java
builder.setX(x).setY(y).setZ(z).build();
```

## Real-World Examples

1. StringBuilder/StringBuffer in Java
   ```java
   StringBuilder sb = new StringBuilder()
       .append("Hello")
       .append(" ")
       .append("World");
   ```

2. Stream API in Java
   ```java
   List<String> result = list.stream()
       .filter(s -> s.length() > 5)
       .map(String::toUpperCase)
       .collect(Collectors.toList());
   ```

3. HTTP Client Builders
   ```java
   HttpClient client = HttpClient.newBuilder()
       .version(Version.HTTP_2)
       .connectTimeout(Duration.ofSeconds(20))
       .build();
   ```

4. SQL Query Builders (Hibernate, JOOQ)
5. UI Component Builders (Android, JavaFX)

## Best Practices

1. Use for 4+ Parameters: Consider Builder when you have 4 or more parameters
2. Make Product Immutable: Don't provide setters in the Product class
3. Validate in build(): Perform validation in the build() method
4. Required vs Optional: Use constructor for required fields, setters for optional
5. Return this: Always return this from setter methods for chaining
6. Consider Lombok: Use @Builder annotation in production code
7. Thread Safety: Builders are typically not thread-safe (one per thread)

## Validation Example

```java
public Computer build() {
    // Validation logic
    if (CPU == null || RAM == null) {
        throw new IllegalStateException("CPU and RAM are required");
    }
    return new Computer(this);
}
```

## When NOT to Use

1. Simple objects with 2-3 parameters
2. When object construction is straightforward
3. When immutability is not required
4. When performance is critical (slight overhead)

## Interview Questions

1. What problem does Builder pattern solve?
    - Telescoping constructor problem and complex object creation

2. Difference between Builder and Factory?
    - Factory creates in one step; Builder creates step by step

3. Why use static nested class for Builder?
    - Logical grouping, access to private constructor, no need for outer class instance

4. How does Builder ensure immutability?
    - Product has no setters, private constructor, and final fields

## Related Patterns

- Factory Method: Creates objects
- Abstract Factory: Creates families of objects
- Prototype: Clones objects
- Singleton: Ensures single instance
- Fluent Interface: Method chaining (used in Builder)

## Summary

The Builder Pattern is ideal when:
- Object has many optional parameters
- You want immutable objects
- Readability and maintainability are priorities
- You need flexible object construction

Avoid when:
- Object is simple with few parameters
- Performance is critical
- Additional complexity is not justified

---