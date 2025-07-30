# Null Object Design Pattern in Java

## 1. Definition
The **Null Object Design Pattern** provides a non-functional (do-nothing) object in place of a `null` reference.  
This avoids explicit `null` checks and makes the code cleaner and more maintainable.

---

## 2. When to Use
- When you frequently check for `null` before using an object.
- When you want **polymorphism** to handle "no operation" cases automatically.

Example scenarios:
- Logging (`NullLogger` that does nothing).
- Payment processing (`NoPayment` means no payment required).

---

## 3. Advantages

- Eliminates null checks (if(obj != null)).
- Makes code more readable.
- Uses polymorphism to handle "do nothing" behavior.
- Avoids NullPointerException.

## Summary

```
Instead of returning null and forcing the caller to handle it, 
return a neutral object that conforms to the interface but does nothing — relying on polymorphism instead of conditional logic.
```