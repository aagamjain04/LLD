# Splitwise Application Architecture

## Overview
The Splitwise application is a command-line expense splitting system that allows users to record expenses and automatically calculate how much each person owes others. It implements clean architecture principles with clear separation of concerns.

## High-Level Architecture

```
┌─────────────────┐
│  Application    │ ── Entry Point & Command Processing
└─────────────────┘
         │
┌─────────────────┐
│  Service Layer  │ ── Business Logic
├─────────────────┤
│ SplitwiseService│
│ ShowService     │
│ UserService     │
└─────────────────┘
         │
┌─────────────────┐
│ Strategy Pattern│ ── Split Algorithms
├─────────────────┤
│ SplitFactory    │
│ Split Interface │
│ - SplitEqually  │
│ - SplitExact    │
│ - SplitPercentage│
└─────────────────┘
         │
┌─────────────────┐
│ Settlement      │ ── Balance Calculation
└─────────────────┘
         │
┌─────────────────┐
│ Repository      │ ── Data Management
├─────────────────┤
│ UserRepository  │
└─────────────────┘
         │
┌─────────────────┐
│ DTOs & Models   │ ── Data Transfer Objects
├─────────────────┤
│ User            │
│ Expense         │
└─────────────────┘
```

## Core Components

### 1. Application Layer

#### [`Application.java`](Application.java)
- **Purpose**: Main entry point for the application
- **Responsibilities**:
  - Reads commands from input file
  - Parses command-line arguments
  - Delegates to appropriate services
  - Handles exceptions and error cases
- **Supported Commands**:
  - `EXPENSE`: Records a new expense
  - `SHOW`: Displays balance information

```java
// Command processing flow
switch (inp[0]){
    case Commands.SHOW:
        splitwiseService.show(inp);
        break;
    case Commands.EXPENSE:
        splitwiseService.splitExpense(inp);
        break;
}
```

### 2. Service Layer

#### [`SplitwiseService.java`](service/SplitwiseService.java)
- **Purpose**: Core business logic coordinator
- **Key Methods**:
  - `show(String[] inp)`: Delegates to ShowService for balance display
  - `splitExpense(String[] inp)`: Handles expense splitting logic

**Expense Processing Flow**:
1. Parse input parameters (user, amount, members, expense type)
2. Get appropriate split strategy from SplitFactory
3. Delegate to strategy for processing

#### [`ShowService.java`](service/ShowService.java)
- **Purpose**: Manages balance display logic
- **Key Methods**:
  - `showById(String id)`: Shows balances for specific user
  - `showAll()`: Shows all outstanding balances
- **Features**:
  - Handles "No Balance" scenarios
  - Filters positive balances only
  - Formats output for readability

#### [`UserService.java`](service/UserService.java)
- **Purpose**: User lifecycle management
- **Responsibilities**:
  - Creates new users
  - Registers users in repository
  - Maintains user data integrity

### 3. Split Strategy Pattern

#### [`Split.java`](service/Split/Split.java) - Interface
```java
public interface Split {
    void process(String user, Double amount, List<String> members, List<String> shares);
}
```

#### [`SplitFactory.java`](service/SplitFactory.java)
- **Purpose**: Factory for creating split strategy instances
- **Pattern**: Factory Pattern
- **Supported Types**: EQUAL, EXACT, PERCENT

#### Strategy Implementations

##### [`SplitEqually.java`](service/Split/SplitEqually.java)
- **Logic**: Divides total amount equally among all members
- **Formula**: `eachShare = amount / memberCount`
- **Use Case**: Dinner bills, shared utilities

##### [`SplitExact.java`](service/Split/SplitExact.java)
- **Logic**: Uses exact amounts specified for each member
- **Use Case**: When each person's contribution is predetermined

##### [`SplitPercentage.java`](service/Split/SplitPercentage.java)
- **Logic**: Splits based on percentage shares
- **Formula**: `borrowedAmount = (percentage * totalAmount) / 100`
- **Use Case**: Business expenses with different contribution ratios

### 4. Settlement Engine

#### [`Settlement.java`](service/Split/Settlement.java)
- **Purpose**: Core algorithm for balance calculation and debt simplification
- **Algorithm**:
  ```java
  double result = amount2 - amount1 + share;
  ```
- **Logic**:
  - Calculates net balances between users
  - Minimizes number of transactions
  - Updates user expense maps
- **Debt Simplification**: Reduces complex multi-party debts to minimal transactions

### 5. Data Layer

#### Repository Pattern
##### [`UserRepository.java`](UserRepository/UserRepository.java)
- **Purpose**: Centralized user data management
- **Storage**: In-memory using static collections
- **Data Structures**:
  - `List<User> userList`: Ordered list of all users
  - `Map<String, User> stringUserMap`: Fast user lookup by name
- **Performance**: O(1) user lookups, O(n) iterations

#### Data Transfer Objects

##### [`User.java`](dto/User.java)
```java
public class User {
    String name;                    // User identifier
    Map<String,Expense> lendToMap;  // Expense tracking with other users
}
```
- **Purpose**: Represents a user in the system
- **Key Feature**: `lendToMap` tracks all financial relationships

##### [`Expense.java`](dto/Expense.java)
```java
public class Expense {
    String userId;  // The other user in the relationship
    Double amount;  // Amount owed/lent
}
```
- **Purpose**: Represents a financial relationship between two users
- **Interpretation**:
  - Positive amount: Current user owes the other user
  - Negative amount: Other user owes the current user

### 6. Configuration & Constants

#### [`ExpenseType.java`](enums/ExpenseType.java)
```java
public enum ExpenseType {
    EQUAL("EQUAL"),
    EXACT("EXACT"),
    PERCENT("PERCENT");
}
```

#### [`Commands.java`](constants/Commands.java)
```java
public class Commands {
    public static final String EXPENSE = "EXPENSE";
    public static final String SHOW = "SHOW";
}
```

#### [`Error.java`](constants/Error.java)
```java
public class Error {
    public static final String NOBALANCE = "No Balances";
}
```

## Design Patterns Used

### 1. Strategy Pattern
- **Implementation**: Split strategies (Equal, Exact, Percentage)
- **Benefit**: Easy to add new splitting algorithms
- **Location**: `service/Split/` package

### 2. Factory Pattern
- **Implementation**: SplitFactory creates appropriate split strategy
- **Benefit**: Encapsulates object creation logic
- **Decision Point**: ExpenseType enum

### 3. Repository Pattern
- **Implementation**: UserRepository abstracts data access
- **Benefit**: Centralized data management
- **Storage**: In-memory collections

## Data Flow Sequence

```
User Input → Application → SplitwiseService → SplitFactory → Split Strategy → Settlement → UserRepository
```

### Expense Processing Sequence:
1. **Input Parsing**: Extract user, amount, members, expense type, shares
2. **User Validation**: Check/create users in repository
3. **Strategy Selection**: SplitFactory returns appropriate strategy
4. **Share Calculation**: Strategy calculates individual shares
5. **Settlement**: Update balances in user repository
6. **Persistence**: Store updated relationships

### Balance Display Sequence:
1. **Command Processing**: Parse SHOW command
2. **User Lookup**: Find user(s) in repository
3. **Balance Calculation**: Iterate through expense relationships
4. **Filtering**: Show only positive balances
5. **Output**: Format and display results

## Key Features

### 1. Debt Simplification
- **Algorithm**: Net balance calculation between users
- **Benefit**: Minimizes number of actual transactions needed
- **Implementation**: Settlement class handles complex calculations

### 2. Multiple Split Types
- **Equal**: Fair split among all participants
- **Exact**: Custom amounts for each person
- **Percentage**: Proportional splitting based on percentages

### 3. Balance Management
- **Tracking**: Who owes whom and how much
- **Updates**: Real-time balance updates with new expenses
- **Display**: Clean, readable balance summaries

### 4. Command-Based Interface
- **Simplicity**: Easy-to-use text commands
- **Extensibility**: Easy to add new command types
- **File Input**: Batch processing from input files

## Example Usage

### Input File Format:
```
EXPENSE Alice 1000 4 Alice Bob Charlie David EQUAL
EXPENSE Bob 500 2 Alice Bob EXACT 300 200
SHOW
SHOW Alice
```

### Expected Output:
```
Bob owes Alice : 250.0
Charlie owes Alice : 250.0
David owes Alice : 250.0
Alice owes Bob : 300.0
```

## Technical Specifications

### Memory Complexity:
- **User Storage**: O(n) where n = number of users
- **Expense Relationships**: O(n²) worst case for complete graph
- **Lookup Performance**: O(1) for user retrieval

### Time Complexity:
- **Add Expense**: O(m) where m = number of members
- **Show Balances**: O(n*m) where n = users, m = average relationships
- **Settlement**: O(m) for each expense processing

## Limitations & Scalability Considerations

### Current Limitations:
1. **No Persistence**: Data lost between sessions
2. **Memory Bound**: Static collections limit scalability
3. **Single-threaded**: Not thread-safe
4. **No Validation**: Limited input validation
5. **Fixed Commands**: Only EXPENSE and SHOW supported

### Scalability Improvements:
1. **Database Integration**: Replace in-memory storage
2. **Caching Layer**: Add Redis for frequently accessed data
3. **Thread Safety**: Implement concurrent access handling
4. **API Layer**: REST API for remote access
5. **Validation Framework**: Comprehensive input validation

### Potential Enhancements:
1. **Currency Support**: Multi-currency handling
2. **Categories**: Expense categorization
3. **Groups**: Hierarchical group management
4. **Notifications**: User notifications for new expenses
5. **Reports**: Detailed expense reports and analytics

## File Structure
```
Splitwise/
├── Application.java              # Main entry point
├── constants/
│   ├── Commands.java            # Command constants
│   └── Error.java               # Error messages
├── dto/
│   ├── User.java                # User data model
│   └── Expense.java             # Expense data model
├── enums/
│   └── ExpenseType.java         # Split type enumeration
├── service/
│   ├── SplitwiseService.java    # Main business logic
│   ├── ShowService.java         # Display logic
│   ├── UserService.java         # User management
│   ├── SplitFactory.java        # Strategy factory
│   └── Split/
│       ├── Split.java           # Strategy interface
│       ├── SplitEqually.java    # Equal split implementation
│       ├── SplitExact.java      # Exact split implementation
│       ├── SplitPercentage.java # Percentage split implementation
│       └── Settlement.java      # Balance calculation engine
├── UserRepository/
│   └── UserRepository.java      # Data access layer
└── resources/
    └── input.txt               # Sample input file
```

## Conclusion

The Splitwise application demonstrates solid software engineering principles with:
- **Clean Architecture**: Clear separation between layers
- **Design Patterns**: Strategic use of Strategy and Factory patterns
- **Extensibility**: Easy to add new features and split types
- **Maintainability**: Well-organized code structure
- **Performance**: Efficient algorithms for common operations

This architecture provides a strong foundation for building a production-ready expense splitting application with additional features and scalability improvements.
