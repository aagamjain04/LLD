# SOLID Principles of OOPS

## Advantages:
- Avoid duplicate code
- Easy to maintain
- Easy to understand
- Flexible software
- Reduce complexity

## SOLID Principles
- **S** - Single Responsibility Principle
- **O** - Open / Close Principle
- **L** - Liskov Substitution Principle
- **I** - Interface Segregation Principle
- **D** - Dependency Inversion Principle

---

## Single Responsibility Principle

The **SRP** states that a class should have only one reason to change, meaning it should have only one job or responsibility.

**Code** - [SingleResponsibility.java](https://github.com/aagamjain04/LLD/blob/main/SolidPrinciples/src/SingleResponsibility.java)

### The original Employee class was handling multiple responsibilities:
- Employee data management
- Database operation
- Tax Calculation
- Email communication

### Improved Version:
We split these responsibilities into separate classes:
- **Employee**: Only manages employee data
- **EmployeeRepository**: Handles all database operations
- **SalaryCalculator**: Manages salary-related calculations
- **EmailService**: Handles all email communications

---

## Open-Closed Principle

The **OCP** states that software entities should be open for extension but closed for modifications. This means you should be able to add new functionality without changing existing code.

**Code** - [OpenClosedPrinciple.java](https://github.com/aagamjain04/LLD/blob/main/SolidPrinciples/src/OpenClosedPrinciple.java)

### The bad approach:
- Uses if-else statements to handle different notification types
- Must modify existing code to add new notification methods
- Violates OCP because it’s not closed for modification

### The good approach:
- Has a simple **Notifier** interface that defines the contract
- Each notification type implements this interface
- Can add new notification types without changing the existing **NotificationService** class

---

## Liskov-Substitution Principle

The **LSP** states that if class B is subtype of class A, then we should be able to replace the object of A with B without breaking the behaviour of the program.

### The bad approach:
- The **Bike** interface assumes all bikes have engines (by including **turnOnEngine()** method)
- The **Bicycle** implementation cannot fulfill this throws an exception
- **Code** - [LiskovSubstitutionViolation.java]()

### The good approach:
- Subclass should extend the capability of parent class and not narrow it down.
- Only include methods in the interface that all implementations can support.
- **Code** - [LiskovSubstitutionSolution.java]()


---