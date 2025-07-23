# 🎟️ Concurrency Control in Movie Seat Booking System

When multiple users try to book the same seat at the same time, concurrency control ensures consistent and correct data — avoiding issues like double booking.

---

## 🔐 1. Pessimistic Concurrency Control

### ✅ Mechanism:
- Assumes **conflicts are likely**.
- Locks the seat during selection or booking.
- Other users are blocked from accessing the locked seat until the transaction is complete.

### 🧠 Example:
- User A selects Seat 5 → system locks the seat.
- User B tries to select Seat 5 → receives a “seat unavailable” message or waits.

### ✅ Pros:
- Prevents double booking reliably.
- Ensures strong consistency.

### ❌ Cons:
- Locking introduces latency.
- May lead to **deadlocks** or **timeouts** under high load.
- Not very scalable in high-concurrency systems.

---

## 🔓 2. Optimistic Concurrency Control

### ✅ Mechanism:
- Assumes **conflicts are rare**.
- Allows multiple users to operate in parallel.
- Conflicts are detected and handled during the **commit phase** using techniques like **versioning**.

### 🧠 Example:
- User A and B both select Seat 5.
- During final payment, the system checks if the seat is still available.
- If it is → booking succeeds.
- If not → transaction fails or is retried.

### 🔁 Versioning Example

Assume we have a `seats` table with a `version` column:

| seat_id | is_booked | version |
|---------|-----------|---------|
|   5     |   false   |    1    |

1. **User A** and **User B** both read Seat 5, with version = 1.
2. **User A** books the seat:
   ```sql
   UPDATE seats
   SET is_booked = true, version = 2
   WHERE seat_id = 5 AND version = 1;
   -- ✅ Succeeds
   ```
3. **User B** tries to book:
   ```sql
   UPDATE seats
   SET is_booked = true, version = 2
   WHERE seat_id = 5 AND version = 1;
   -- ❌ Fails (version is now 2)
   ```

> 🔍 The mismatch in `version` helps detect conflicts without locking.

### ✅ Pros:
- Highly scalable and fast under low contention.
- No locking overhead or deadlocks.

### ❌ Cons:
- Conflicts are detected **late**.
- Requires **retry** or error-handling logic.
- May impact UX if booking fails after multiple steps.

---

## ⚖️ 3. Pessimistic vs Optimistic — Comparison

| Feature             | Pessimistic           | Optimistic             |
|---------------------|------------------------|-------------------------|
| Conflict Assumption | Likely                 | Rare                    |
| Locking             | Yes (explicit lock)    | No                      |
| Performance         | Slower under load      | Faster & more scalable |
| Risk of Deadlocks   | Yes                    | No                      |
| Conflict Handling   | Prevented via locks    | Detected via checks     |
| Best Use Case       | High-traffic bookings  | Low to moderate traffic |

---

## 🔄 4. Hybrid Approach (Recommended)

Combine both techniques to get the best of both worlds:

- **Short-lived lock** during seat selection → Pessimistic
- **Version check** during final payment → Optimistic

### 🛠️ Example Flow:
1. User selects a seat → temporarily lock it (expires after 2 mins).
2. At payment, check if:
    - Lock is still held,
    - Seat is still available,
    - Version is unchanged.

✅ Ensures strong consistency with minimal locking time.

---

## ✅ Final Summary

| Scenario                     | Recommended Approach     |
|------------------------------|---------------------------|
| High contention              | Pessimistic               |
| Low contention               | Optimistic                |
| Want scalability + safety    | Hybrid                    |

- Use **pessimistic** for critical consistency, e.g. first-day movie tickets.
- Use **optimistic** for smoother user experience in low-risk cases.
- **Hybrid** offers a scalable and safe middle-ground in real-world systems.

---
