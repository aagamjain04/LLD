# Factory Design Pattern - Brief Overview

## Concept
The Factory Design Pattern is a creational pattern that abstracts object creation logic from client code. It provides an interface to create objects without exposing instantiation logic.

## Key Components

- **Product Interface**: Defines the interface for objects created by the factory
- **Concrete Products**: Classes that implement the product interface
- **Factory**: Class responsible for creating product objects



## Benefits

- **Encapsulation**: Hides creation logic from client code
- **Loose coupling**: Client code doesn't depend on concrete product classes
- **Single Responsibility**: Separates creation from business logic
- **Extensibility**: Easy to add new product types

## When to Use

- When a class can't anticipate which class of objects it must create
- When you want to localize knowledge of which class gets created
- When you want to decouple object creation from object usage