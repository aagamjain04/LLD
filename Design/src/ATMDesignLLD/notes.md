# ATM System Design - Low Level Design Interview Notes

## System Overview
A complete ATM system implementation demonstrating the **State Pattern** and **Chain of Responsibility Pattern** for handling ATM operations like card insertion, PIN authentication, balance checking, and cash withdrawal.

---

## Core Design Patterns Used

### 1. **State Pattern**
- **Purpose**: Manages different states of the ATM machine
- **States**:
    - `IdleState` - Initial state, waiting for card insertion
    - `HasCardState` - Card inserted, waiting for PIN
    - `SelectOperationState` - PIN verified, user selects operation
    - `CashWithdrawalState` - Processing withdrawal
    - `CheckBalanceState` - Displaying balance

### 2. **Chain of Responsibility Pattern**
- **Purpose**: Handles cash dispensing with different denominations
- **Chain**: 2000 → 500 → 100
- **Processors**:
    - `TwoThousandWithdrawProcessor`
    - `FiveHundredWithdrawProcessor`
    - `OneHundredWithdrawProcessor`

### 3. **Singleton Pattern**
- **Class**: `ATM`
- **Implementation**: Eager initialization
- Ensures only one ATM instance exists

---

## Class Structure

### Main Classes

#### **ATM** (Singleton)
```java
- currentATMState: ATMState
- atmBalance: int
- noOfTwoThousandNotes, noOfFiveHundredNotes, noOfOneHundredNotes: int
```
**Key Methods**:
- `getATMObject()` - Returns singleton instance
- `setAtmBalance()` - Initialize ATM with cash
- `deductATMBalance()` - Reduce balance after withdrawal
- `deductXNotes()` - Track individual note counts

#### **ATMState** (Abstract)
Base class for all states with default implementations that print error messages.

**Methods**:
- `insertCard()`
- `authenticatePin()`
- `selectOperation()`
- `cashWithdrawal()`
- `displayBalance()`
- `returnCard()`
- `exit()`

#### **Card**
```java
- cardNumber, cvv, holderName, expiryDate, pin: int/String
- bankAccount: UserBankAccount
```
**Key Methods**:
- `isCorrectPINEntered()` - Validates PIN
- `getBankBalance()` - Returns account balance
- `deductBankBalance()` - Withdraws money from account

---

## State Transition Flow

```
IdleState 
    → insertCard() 
    → HasCardState
        → authenticatePin()
        → SelectOperationState
            → selectOperation(CASH_WITHDRAWAL)
            → CashWithdrawalState
                → cashWithdrawal()
                → exit() → IdleState
            
            OR
            
            → selectOperation(BALANCE_CHECK)
            → CheckBalanceState
                → displayBalance()
                → exit() → IdleState
```

---

## Key Implementation Details

### State Implementations

#### **IdleState**
- Only allows `insertCard()` operation
- Transitions to `HasCardState`

#### **HasCardState**
- Constructor prompts for PIN and sets it
- `authenticatePin()` validates and moves to `SelectOperationState` or exits on failure

#### **SelectOperationState**
- Shows available operations (CASH_WITHDRAWAL, BALANCE_CHECK)
- Routes to appropriate state based on selection

#### **CashWithdrawalState**
- Validates ATM has sufficient cash
- Validates user account has sufficient balance
- Deducts from both ATM and user account
- Uses Chain of Responsibility to dispense notes
- Always exits to `IdleState` after completion

#### **CheckBalanceState**
- Displays user's bank balance
- Returns card and exits to `IdleState`

---

## Cash Withdrawal Chain Logic

### TwoThousandWithdrawProcessor
```
1. Calculate required 2000 notes = amount / 2000
2. If available: deduct and pass remainder to next
3. If insufficient: use all available, calculate new remainder
4. Pass remainder to FiveHundredWithdrawProcessor
```

### FiveHundredWithdrawProcessor
```
Same logic with 500 denomination
→ Pass to OneHundredWithdrawProcessor
```

### OneHundredWithdrawProcessor
```
Same logic with 100 denomination
→ If remainder exists: Print "Denomination Error"
```

**Example**: Withdrawing ₹2700
- Use 1 × ₹2000 note → Remaining: ₹700
- Use 1 × ₹500 note → Remaining: ₹200
- Use 2 × ₹100 notes → Remaining: ₹0

---

## Critical Design Decisions

### 1. **Why State Pattern?**
- ATM behavior changes based on current state
- Each state has specific allowed operations
- Prevents invalid operations (e.g., withdrawing cash in IdleState)
- Clean separation of concerns

### 2. **Why Chain of Responsibility?**
- Flexible denomination handling
- Easy to add/remove denominations
- Each processor handles one denomination independently
- Automatic cascading to lower denominations

### 3. **Validation Checks**
- ATM balance check before withdrawal
- User account balance check
- PIN authentication
- Denomination availability check

### 4. **Error Handling**
- Default error messages in `ATMState` for invalid operations
- Specific error messages for insufficient funds
- Denomination error when exact amount can't be dispensed

---

## Interview Discussion Points

### Extensibility
**Q: How would you add a new operation like PIN change?**
- Create `ChangePinState` extending `ATMState`
- Add `PIN_CHANGE` to `TransactionType` enum
- Add case in `SelectOperationState.selectOperation()`

**Q: How would you add a new denomination (₹200 notes)?**
- Create `TwoHundredWithdrawProcessor`
- Insert into chain at appropriate position
- Update `ATM` class to track ₹200 notes



### SOLID Principles

**Single Responsibility**:
- Each state handles only its specific operations
- Each processor handles one denomination

**Open/Closed**:
- Easy to add new states without modifying existing ones
- Easy to add new denominations in the chain

**Liskov Substitution**:
- All states can be substituted for `ATMState`
- All processors implement `CashWithdrawProcessor`

**Interface Segregation**:
- `ATMState` provides all operations but states override only relevant ones

**Dependency Inversion**:
- States depend on `ATMState` abstraction
- Processors depend on `CashWithdrawProcessor` interface

---

## Sample Output Flow
```
Balance: 3500
2kNotes: 1
500Notes: 2
100Notes: 5
Card is inserted
enter your card pin number
Please select the Operation
CASH_WITHDRAWAL
BALANCE_CHECK
Please enter the Withdrawal Amount
Please collect your card
Exiting...
Balance: 800
2kNotes: 0
500Notes: 1
100Notes: 3
```

---

## Key Takeaways for Interview

1. **Design Patterns**: Clearly explain why State and Chain of Responsibility are used
2. **State Management**: Walk through complete state transition flow
3. **Error Handling**: Discuss validation at multiple levels
4. **Extensibility**: Demonstrate how to add new features
5. **Trade-offs**: Discuss missing features and how to add them
6. **Real-world Considerations**: Concurrency, security, hardware integration