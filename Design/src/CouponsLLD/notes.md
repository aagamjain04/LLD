# Strategy vs Decorator for Coupon System (LLD Interview Notes)

## Problem Context
We need to apply different **coupons** on a shopping cart.
- Coupons calculate discounts differently
- Coupon selection happens at runtime
- New coupon types should be added without modifying existing code

---

## Why Strategy Pattern is Used

### Intent of Strategy Pattern
> Define a family of algorithms, encapsulate each one, and make them interchangeable.

### Mapping to Coupon System
| Strategy Concept | Coupon System |
|------------------|--------------|
| Algorithm | Discount calculation |
| Strategy Interface | `Coupon` |
| Concrete Strategies | `FlatCoupon`, `PercentageCoupon`, `CategoryCoupon` |
| Context | `CouponEngine` |

### Key Reasons
- Coupons are **independent algorithms**
- Runtime selection of coupon logic
- Cart structure is unchanged
- Follows **Open/Closed Principle**
- Clean separation of concerns

✔ **Best fit for coupon systems**

---

## Why Decorator Pattern is NOT Suitable

### Intent of Decorator Pattern
> Add responsibilities to an object dynamically.

### Issues with Decorator for Coupons
- Coupons are **rules**, not cart features
- Forces coupon logic inside cart hierarchy
- Discount result depends on wrapping order
- Causes **class explosion**
- Violates **Single Responsibility Principle**

❌ **Over-engineered and fragile for this use case**

---

## Strategy vs Decorator Comparison

| Aspect | Strategy | Decorator |
|------|---------|-----------|
| Primary use | Algorithm selection | Feature extension |
| Coupon logic | ✔ Natural fit | ❌ Forced |
| Runtime flexibility | ✔ High | ⚠ Order dependent |
| SRP compliance | ✔ Yes | ❌ No |
| Coupon stacking control | ✔ Centralized | ❌ Distributed |

---

## When Decorator *Would* Make Sense
- Layered pricing models:
    - Tax
    - Shipping
    - Platform fees
    - Loyalty discounts
- Permanent cart behavior changes

Example:
BasePrice → Tax → Shipping → Discount
