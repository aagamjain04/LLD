# Database Isolation Levels

## Overview
Isolation levels define the degree to which transactions are isolated from each other in a database system. They determine how and when changes made by one transaction become visible to other concurrent transactions.

## Why Isolation Levels Matter
In multi-user database systems, multiple transactions can execute concurrently. Without proper isolation, this can lead to various concurrency problems. Isolation levels help balance between:
- **Data consistency** (preventing anomalies)
- **Performance** (allowing more concurrency)

## The ACID Properties
Isolation is the "I" in ACID:
- **Atomicity**: All or nothing execution
- **Consistency**: Database remains in valid state
- **Isolation**: Transactions don't interfere with each other
- **Durability**: Committed changes are permanent

## Common Concurrency Problems

### 1. Dirty Read
Reading **uncommitted** data from another transaction.

```
Transaction A          Transaction B
-----------           -----------
                      UPDATE account SET balance = 500
READ balance (500)    
                      ROLLBACK
```
Transaction A reads 500, but Transaction B rolls back. The data was never committed.

### 2. Non-Repeatable Read
Reading the **same row twice** returns different data because another transaction modified and committed it.

```
Transaction A          Transaction B
-----------           -----------
READ balance (1000)    
                      UPDATE account SET balance = 500
                      COMMIT
READ balance (500)    
```
Same query in Transaction A returns different results.

### 3. Phantom Read
A query returns **different sets of rows** when executed twice because another transaction inserted/deleted rows.

```
Transaction A                Transaction B
-----------                 -----------
SELECT COUNT(*) (10 rows)    
                            INSERT INTO accounts VALUES (...)
                            COMMIT
SELECT COUNT(*) (11 rows)    
```
The number of rows changed between reads.

### 4. Lost Update
Two transactions read the same data and then update it, causing one update to be lost.

```
Transaction A          Transaction B
-----------           -----------
READ balance (1000)    READ balance (1000)
                      UPDATE balance = 1000 + 100 = 1100
                      COMMIT
UPDATE balance = 1000 - 50 = 950
COMMIT
```
Transaction B's update is lost.

## The Four Standard Isolation Levels

### 1. READ UNCOMMITTED (Level 0)
**Lowest isolation level**

**Characteristics:**
- Transactions can read uncommitted changes from other transactions
- No locks are acquired for reading
- Maximum concurrency, minimum consistency

**Problems Allowed:**
- ✗ Dirty Read
- ✗ Non-Repeatable Read
- ✗ Phantom Read

**Use Case:** Rarely used; only for scenarios where approximate data is acceptable (like reporting dashboards showing rough estimates)

**Example:**
```sql
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
BEGIN TRANSACTION;
SELECT * FROM accounts WHERE id = 1;
COMMIT;
```

### 2. READ COMMITTED (Level 1)
**Default in many databases (PostgreSQL, Oracle, SQL Server)**

**Characteristics:**
- Only reads committed data
- Shared locks are acquired and released immediately after reading
- Other transactions can modify data between reads

**Problems Allowed:**
- ✓ Prevents Dirty Read
- ✗ Non-Repeatable Read
- ✗ Phantom Read

**Use Case:** Most common for general-purpose applications; good balance between consistency and performance

**Example:**
```sql
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
BEGIN TRANSACTION;
SELECT * FROM accounts WHERE id = 1;
-- Another transaction can modify this row now
SELECT * FROM accounts WHERE id = 1; -- Might get different data
COMMIT;
```

### 3. REPEATABLE READ (Level 2)
**Default in MySQL/InnoDB**

**Characteristics:**
- Guarantees that if a row is read twice, it will have the same values
- Shared locks are held until transaction completes
- Prevents other transactions from modifying read rows

**Problems Allowed:**
- ✓ Prevents Dirty Read
- ✓ Prevents Non-Repeatable Read
- ✗ Phantom Read (in some databases)

**Note:** In MySQL/InnoDB, phantom reads are also prevented using next-key locks.

**Use Case:** Financial applications, reporting that requires consistent snapshots

**Example:**
```sql
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
BEGIN TRANSACTION;
SELECT * FROM accounts WHERE id = 1; -- balance = 1000
-- Another transaction CANNOT modify this row
SELECT * FROM accounts WHERE id = 1; -- balance = 1000 (guaranteed same)
COMMIT;
```

### 4. SERIALIZABLE (Level 3)
**Highest isolation level**

**Characteristics:**
- Transactions execute as if they were serial (one after another)
- Complete isolation from other transactions
- Uses range locks to prevent phantom reads
- Lowest concurrency, highest consistency

**Problems Allowed:**
- ✓ Prevents Dirty Read
- ✓ Prevents Non-Repeatable Read
- ✓ Prevents Phantom Read

**Use Case:** Critical operations where absolute consistency is required (banking transactions, inventory systems)

**Example:**
```sql
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRANSACTION;
SELECT SUM(balance) FROM accounts WHERE branch_id = 5;
-- No other transaction can insert, update, or delete rows matching this criteria
SELECT SUM(balance) FROM accounts WHERE branch_id = 5; -- Guaranteed same result
COMMIT;
```

## Isolation Levels Comparison Table

| Isolation Level    | Dirty Read | Non-Repeatable Read | Phantom Read | Performance | Concurrency |
|-------------------|------------|---------------------|--------------|-------------|-------------|
| READ UNCOMMITTED  | ✗ Possible | ✗ Possible          | ✗ Possible   | Highest     | Maximum     |
| READ COMMITTED    | ✓ Prevented| ✗ Possible          | ✗ Possible   | High        | High        |
| REPEATABLE READ   | ✓ Prevented| ✓ Prevented         | ✗ Possible*  | Medium      | Medium      |
| SERIALIZABLE      | ✓ Prevented| ✓ Prevented         | ✓ Prevented  | Lowest      | Minimum     |

*Note: MySQL/InnoDB prevents phantom reads even at REPEATABLE READ level

## How Databases Implement Isolation

### 1. Locking Mechanisms
- **Shared Locks (S)**: Multiple transactions can read
- **Exclusive Locks (X)**: Only one transaction can write
- **Range Locks**: Prevent phantom reads

### 2. Multi-Version Concurrency Control (MVCC)
Used by PostgreSQL, MySQL/InnoDB, Oracle:
- Each transaction sees a snapshot of data
- No locks needed for reading
- Better performance than pure locking

### 3. Timestamp Ordering
Transactions are ordered by timestamps to prevent conflicts

## Setting Isolation Levels

### MySQL
```sql
-- Session level
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- Global level
SET GLOBAL TRANSACTION ISOLATION LEVEL REPEATABLE READ;

-- Per transaction
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
-- your queries
COMMIT;
```

### PostgreSQL
```sql
-- Session level
SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- Per transaction
BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
-- your queries
COMMIT;
```

### SQL Server
```sql
-- Session level
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

-- Per transaction with table hints
SELECT * FROM accounts WITH (READUNCOMMITTED);
```

### Java (JDBC)
```java
Connection conn = DriverManager.getConnection(url, user, password);
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
// Options:
// Connection.TRANSACTION_READ_UNCOMMITTED
// Connection.TRANSACTION_READ_COMMITTED
// Connection.TRANSACTION_REPEATABLE_READ
// Connection.TRANSACTION_SERIALIZABLE
```

### Spring Boot
```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void transferMoney(Account from, Account to, double amount) {
    // transaction logic
}

// Other options:
// Isolation.READ_UNCOMMITTED
// Isolation.REPEATABLE_READ
// Isolation.SERIALIZABLE
```

## Choosing the Right Isolation Level

### Use READ UNCOMMITTED when:
- Reading approximate/statistical data
- Performance is critical and dirty reads are acceptable
- Rarely recommended

### Use READ COMMITTED when:
- General-purpose OLTP applications
- Good balance needed between consistency and performance
- **Most common choice**

### Use REPEATABLE READ when:
- Need consistent reads within a transaction
- Reporting or analytics that require snapshot consistency
- Financial calculations

### Use SERIALIZABLE when:
- Absolute data consistency is critical
- Complex transactions with multiple queries
- Banking, inventory, or audit systems
- Can tolerate lower throughput

## Trade-offs

### Higher Isolation → Lower Performance
```
SERIALIZABLE      ████████████ (Most locks, lowest concurrency)
REPEATABLE READ   ████████     (Moderate locks)
READ COMMITTED    ████         (Few locks)
READ UNCOMMITTED  █            (No locks)
```

### Higher Isolation → Better Consistency
```
SERIALIZABLE      ████████████ (Perfect consistency)
REPEATABLE READ   ████████     (Good consistency)
READ COMMITTED    ████         (Basic consistency)
READ UNCOMMITTED  █            (Minimal consistency)
```

## Best Practices

1. **Use the default** (READ COMMITTED) unless you have a specific reason to change
2. **Keep transactions short** to reduce lock contention
3. **Test with concurrent users** to identify isolation issues
4. **Use optimistic locking** when appropriate (version columns)
5. **Monitor deadlocks** and adjust isolation levels if needed
6. **Consider application-level solutions** before increasing isolation
7. **Document why** you chose a specific isolation level

## Real-World Examples

### Banking Transfer (SERIALIZABLE or REPEATABLE READ)
```sql
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRANSACTION;

SELECT balance FROM accounts WHERE id = 1 FOR UPDATE;
SELECT balance FROM accounts WHERE id = 2 FOR UPDATE;

UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;

COMMIT;
```

### E-commerce Inventory (REPEATABLE READ)
```sql
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
BEGIN TRANSACTION;

SELECT quantity FROM products WHERE id = 101;
-- Check if quantity > 0
UPDATE products SET quantity = quantity - 1 WHERE id = 101;
INSERT INTO orders (product_id, customer_id) VALUES (101, 555);

COMMIT;
```

### Analytics Dashboard (READ UNCOMMITTED)
```sql
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
SELECT COUNT(*), AVG(amount) FROM transactions;
-- Approximate counts are acceptable for dashboard
```

## Summary

Isolation levels are a critical aspect of database transaction management that balance data consistency with system performance. Understanding them helps you make informed decisions about your application's data integrity requirements and optimize for the right level of concurrency.

**Key Takeaways:**
- READ COMMITTED is the default and most commonly used
- Higher isolation = better consistency but lower performance
- Choose isolation level based on your application's requirements
- Test concurrent scenarios to validate your choice
- Document isolation level decisions in your codebase