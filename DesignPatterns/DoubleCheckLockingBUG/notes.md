# Double-Checked Locking of Singleton Pattern & Its Fix

## Introduction

Double-Checked Locking (DCL) is an optimization pattern used with the Singleton design pattern to reduce the overhead of acquiring a lock by first testing the locking criterion without actually acquiring the lock. The pattern checks if an instance exists before synchronizing, and then checks again inside the synchronized block before creating the instance.

### Basic DCL Implementation (Broken)

```java
public class Singleton {
    private static Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {              // First check (no locking)
            synchronized (Singleton.class) {
                if (instance == null) {      // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

**Intent**: Only synchronize when necessary (i.e., when instance is null), avoiding the performance cost of synchronization on every call after initialization.

**Problem**: This implementation is broken in multi-threaded environments due to two critical issues related to the Java Memory Model.

---

## Issue 1: Instruction Reordering Issue

### The Problem

Modern compilers and processors can reorder instructions for performance optimization. The statement `instance = new Singleton()` is not atomic and involves three steps:

1. Allocate memory for the Singleton object
2. Initialize the Singleton object (call constructor)
3. Assign the memory address to the instance variable

The compiler or processor might reorder these steps to:

1. Allocate memory for the Singleton object
2. Assign the memory address to the instance variable (instance is now non-null)
3. Initialize the Singleton object (call constructor)

### Why This Causes Issues

Consider this scenario with two threads:

**Thread A**:
- Enters synchronized block
- Allocates memory for Singleton
- Assigns address to `instance` (now non-null but not initialized)
- *Context switch occurs before constructor completes*

**Thread B**:
- Checks `if (instance == null)`
- Sees instance is NOT null (because memory was assigned)
- Returns the uninitialized instance
- **Crashes or undefined behavior when using the incomplete object**

### Example

```java
// Thread A executes this:
instance = new Singleton();  // Might execute as:
// 1. memory = allocate()
// 2. instance = memory      ← instance is now non-null!
// 3. initialize(memory)      ← constructor hasn't run yet

// Thread B sees non-null instance and uses it before step 3 completes!
```

---

## Issue 2: L1 Caching Issue (Visibility Problem)

### The Problem

Modern CPUs have multiple levels of cache (L1, L2, L3). Each core typically has its own L1 cache. Without proper synchronization or memory barriers, changes made by one thread in its L1 cache might not be immediately visible to other threads.

### Why This Causes Issues

**Thread A** (on Core 1):
- Creates the Singleton instance
- Updates `instance` variable in Core 1's L1 cache
- The value might not be flushed to main memory immediately

**Thread B** (on Core 2):
- Checks `if (instance == null)`
- Reads from Core 2's L1 cache or main memory
- Might see `instance` as still null even though Thread A initialized it
- Creates another instance, breaking the Singleton pattern

### Cache Coherency Issue

```
Core 1 (L1 Cache)          Main Memory          Core 2 (L1 Cache)
instance = 0x5A3B    -->   instance = null  <--  instance = null
(Thread A's view)                                (Thread B's view)
```

Both threads might proceed to create instances because they have different views of memory.

---

## Fix: Usage of Volatile Keyword

### The Solution

Declare the instance variable as `volatile`:

```java
public class Singleton {
    private static volatile Singleton instance;  // volatile keyword added
    
    public static Singleton getInstance() {
        if (instance == null) {              // First check (no locking)
            synchronized (Singleton.class) {
                if (instance == null) {      // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### How Volatile Fixes Both Issues

#### Fixing Instruction Reordering (Issue 1)

The `volatile` keyword establishes a **happens-before relationship**:

- All writes to non-volatile variables that happen before a volatile write are guaranteed to be visible after the volatile write
- The assignment to `instance` (volatile write) creates a memory barrier
- Ensures that object construction completes before the reference is assigned

This prevents the reordering that would assign the reference before initialization completes.

#### Fixing Visibility/Caching (Issue 2)

The `volatile` keyword ensures:

- **Writes to volatile variables** are immediately flushed to main memory
- **Reads of volatile variables** always read from main memory, not from cache
- Provides visibility guarantee across all threads

```
Thread A writes:              Main Memory:           Thread B reads:
instance = 0x5A3B  ========>  instance = 0x5A3B  <=======  instance = 0x5A3B
(immediate flush)                                  (reads from main memory)
```

### Memory Barriers Created by Volatile

- **Write barrier**: Before a volatile write, all previous writes are committed to main memory
- **Read barrier**: After a volatile read, subsequent reads will see the most up-to-date values

---


### Performance Considerations

- DCL with volatile is useful when getInstance() is called frequently and initialization is expensive
- The first check avoids synchronization overhead after initialization
- Modern JVMs optimize volatile reads to be very fast when the value doesn't change

---

## Summary

Double-Checked Locking without `volatile` is broken due to instruction reordering and cache visibility issues. The `volatile` keyword fixes both problems by establishing happens-before relationships and ensuring memory visibility across threads, making DCL a safe and efficient lazy initialization pattern in Java 5+.