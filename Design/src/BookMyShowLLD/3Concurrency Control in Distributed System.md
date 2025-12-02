# Concurrency Control in Distributed Systems

## Overview
Techniques to prevent multiple concurrent transactions/operations from causing data inconsistency, especially when data is spread across multiple databases or locations.

## Why It's Needed
- Multiple app instances may update the same resource simultaneously
- Failures can leave partial updates
- Data is distributed across multiple locations

---

## The Problem: Single vs Distributed Systems

### Single Instance (Works Fine)
```java
public class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;  // Only one thread at a time
    }
}
```
- `synchronized` works within a single JVM
- Only affects threads in that JVM instance

### Distributed System (Fails)
- Multiple servers (Server A, Server B) with separate JVMs
- `synchronized` in Server A doesn't block Server B
- Both can run `increment()` simultaneously → **race conditions**

### Real-World Example: E-commerce Stock
**Scenario:** Last item in stock, two customers order simultaneously
- Customer 1 → Instance 1 → sees stock = 1
- Customer 2 → Instance 2 → also sees stock = 1
- Both decrement to 0
- **Result:** stock = -1 (overselling!)

---

## Deployment Contexts

### 1. Single Database Context
Multiple service instances share the same physical database

### 2. Multiple Database Context
Same operation updates multiple databases or microservices with separate DBs

---

## Solutions Overview

### For Single Database
**Database Locking** (Optimistic/Pessimistic Concurrency Control)

### For Distributed Systems
- **Distributed Locking:** Redis, ZooKeeper, etcd
- **Distributed Coordination:**
    - Two-Phase Commit (2PC)
    - Three-Phase Commit (3PC)
    - Saga Pattern

---

## Core Concepts

### 1. Transaction (ACID)
A unit of work ensuring data consistency

- **Atomicity:** All operations succeed or all fail
- **Consistency:** Maintains database rules and constraints
- **Isolation:** Transactions don't interfere with each other
- **Durability:** Committed changes are permanent

```sql
START TRANSACTION;
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;
COMMIT;
```

### 2. Database Locking
Mechanism to control concurrent access and maintain consistency

#### Lock Types

**Shared Lock (S Lock)**
- Multiple transactions can read concurrently
- Prevents writes to locked data

**Exclusive Lock (X Lock)**
- Allows write operations (insert, update, delete)
- Prevents other transactions from reading or writing

#### Lock Levels
- Row-Level
- Table-Level
- Page-Level

```sql
START TRANSACTION;
SELECT balance FROM accounts WHERE account_id = 1 FOR UPDATE;  -- Exclusive lock
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
COMMIT;
```

#### Importance
- Maintain data consistency
- Prevent race conditions
- Support ACID transactions

### 3. Isolation Levels
Prevent concurrency issues

#### Concurrency Issues
- **Dirty reads:** Reading uncommitted data
- **Non-repeatable reads:** Different results reading same row twice
- **Phantom reads:** New rows appearing in re-run queries

#### Levels (Highest to Lowest Isolation)
1. **SERIALIZABLE** - Strongest consistency, slowest (transactions run sequentially)
2. **REPEATABLE_READ**
3. **READ_COMMITTED**
4. **READ_UNCOMMITTED**

---

## Concurrency Control Strategies

### Optimistic Concurrency Control

**Assumption:** Conflicts are rare

**How it works:**
- No locks during transaction
- Operations proceed freely
- Checks for conflicts only at commit time
- Uses version numbers or timestamps

```sql
START TRANSACTION;
SELECT balance, version FROM accounts WHERE id = 1;  -- balance=500, version=5

-- Compute locally: new balance=400

UPDATE accounts 
SET balance=400, version=6 
WHERE id=1 AND version=5;  -- Fails if version changed

COMMIT;
```

**Key Points:**
- No locks held during operation
- Conflicts detected at commit time
- Best for **low-contention** environments
- Better scalability
- **No deadlocks possible** (no locks held)
- Rolls back and retries on conflict

---

### Pessimistic Concurrency Control

**Assumption:** Conflicts are likely

**How it works:**
- Acquires locks upfront
- Blocks other transactions until lock released
- Prevents conflicts proactively

```sql
START TRANSACTION;
SELECT * FROM accounts WHERE id = 1 FOR UPDATE;  -- Exclusive lock acquired
UPDATE accounts SET balance = balance - 100 WHERE id = 1;
COMMIT;  -- Lock released
```

**Key Points:**
- Best when conflicts are **frequent**
- Cannot tolerate retries
- Critical operations
- Uses S-locks (read) and X-locks (write)
- **Deadlocks possible**

---

## Deadlock Prevention Strategies

### 1. Lock Timeout
- Set maximum wait time for lock acquisition
- Rollback transaction if timeout exceeded

### 2. Wait-For Graph (WFG)
- InnoDB (MySQL) builds internal WFG automatically
- Detects cycles in lock dependencies
- Aborts one transaction (usually with less work)

### 3. Two-Phase Locking (2PL)
Pessimistic protocol ensuring serializability

**Growing Phase:**
- Acquire locks as needed (shared or exclusive)
- Cannot release any locks

**Shrinking Phase:**
- After first lock release, cannot acquire more locks
- Only release remaining locks

---

## Key Takeaways

### Single Database
Use database-level locking and isolation levels
- **Low contention** → Optimistic control
- **High contention/critical updates** → Pessimistic control

### Distributed Systems
Requires advanced solutions:
- Distributed locks (Redis, ZooKeeper, etcd)
- 2PC/3PC protocols
- Saga patterns

### Challenges in Distributed Systems
- Local JVM locks insufficient
- Race conditions across services
- Complex coordination requirements
- Geographic distribution complexity