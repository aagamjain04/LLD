# Order Management System - Low Level Design Documentation

## Overview
This is a comprehensive Order Management System (OMS) that handles the complete order lifecycle from product selection to checkout. The system demonstrates key object-oriented design principles including separation of concerns, strategy pattern, and MVC-like architecture.

## Core Components

### 1. Controllers Layer
The system uses three main controllers to manage different aspects:

#### **UserController**
- Manages user lifecycle operations
- Operations:
    - `addUser(User)` - Register new users
    - `getUserById(int)` - Retrieve user by ID
    - `removeUser(int)` - Remove user from system

#### **WarehouseController**
- Manages warehouse operations and selection
- Uses Strategy Pattern for warehouse selection
- Operations:
    - `addWarehouse(Warehouse)` - Add new warehouse
    - `removeWarehouse(Warehouse)` - Remove warehouse
    - `getWarehouse(WarehouseSelectionStrategy)` - Select warehouse based on strategy

#### **OrderController**
- Manages order lifecycle
- Maintains order history per user
- Operations:
    - `createNewOrder(User, Warehouse)` - Creates new order
    - `removeOrder(Order)` - Cancels/removes order
    - `getOrderByCustomerId(int)` - Retrieves user's orders
    - `getOrderByOrderId(int)` - Retrieves specific order

### 2. User Management

#### **User**
- Properties:
    - `userId` - Unique identifier
    - `userName` - User's name
    - `address` - Delivery address
    - `userCartDetails` - Shopping cart
    - `orderIds` - Order history

#### **Cart**
- Manages shopping cart operations
- Uses Map structure: `productCategoryId -> count`
- Operations:
    - `addItemToCart(productCatId, count)` - Add items
    - `removeItemFromCart(productCatId, count)` - Remove items
    - `emptyCart()` - Clear all items
    - `viewCart()` - Display cart contents

### 3. Product & Inventory Management

#### **Product**
- Basic product entity
- Properties: `id`, `productName`

#### **ProductCategory**
- Groups similar products
- Properties:
    - `productCategoryId` - Category identifier
    - `productCategoryName` - Category name
    - `products` - List of products
    - `price` - Category price
- Operations:
    - `addProduct(Product)` - Add product to category
    - `removeProduct(count)` - Remove multiple products

#### **Inventory**
- Manages all product categories in a warehouse
- Operations:
    - `addCategory(id, name, price)` - Create new category
    - `addProduct(Product, categoryId)` - Add product to category
    - `removeItems(Map<categoryId, count>)` - Bulk remove items

### 4. Warehouse Management

#### **Warehouse**
- Represents physical storage location
- Properties:
    - `inventory` - Product inventory
    - `address` - Warehouse location
- Operations:
    - `removeItemFromInventory(Map)` - Deduct items
    - `addItemToInventory(Map)` - Restock items

#### **WarehouseSelectionStrategy** (Abstract)
- Strategy pattern for warehouse selection
- Implementations:
    - `NearestWarehouseSelectionStrategy` - Selects closest warehouse

### 5. Order Processing

#### **Order**
- Core order entity
- Properties:
    - `orderId` - Unique order identifier
    - `deliveryAddress` - Shipping address
    - `user` - Order owner
    - `productCategoryAndCountMap` - Ordered items
    - `warehouse` - Fulfillment warehouse
    - `invoice` - Order invoice
    - `payment` - Payment details
    - `orderStatus` - Current status (CREATED, DELIVERED, CANCELLED, RETURNED, UNDELIVERED)

- Key Method: `checkOut()`
    1. Updates warehouse inventory
    2. Processes payment
    3. Empties user cart (if payment successful)
    4. Restocks inventory (if payment fails)

#### **Invoice**
- Generates order invoice
- Properties:
    - `totalItemPrice` - Subtotal
    - `totalTax` - Tax amount
    - `totalFinalPrice` - Total amount

#### **Payment System**
- Uses Strategy Pattern for payment methods
- `PaymentMode` Interface:
    - `doPayment()` - Process payment
- Implementations:
    - `UPIPayment` - UPI payment processing

### 6. Product Delivery System (Facade)
Main entry point that coordinates all controllers.

**Key Operations:**
1. `getUser(userId)` - Retrieve user
2. `getWarehouse(strategy)` - Select warehouse
3. `getInventory(warehouse)` - Get available products
4. `addProductToCart(user, product, count)` - Add to cart
5. `placeOrder(user, warehouse)` - Create order
6. `checkout(order)` - Complete purchase

### 7. Supporting Classes

#### **Address**
- Properties: `pinCode`, `city`, `state`
- Used for user and warehouse locations

## System Workflow

### Complete Order Flow:
```
1. User Registration
   └→ UserController.addUser()

2. Browse Products
   └→ WarehouseController.getWarehouse()
   └→ Warehouse.getInventory()

3. Add to Cart
   └→ Cart.addItemToCart()

4. Place Order
   └→ OrderController.createNewOrder()
   └→ Order creation with status = CREATED

5. Checkout
   └→ Order.checkOut()
       ├→ Warehouse.removeItemFromInventory()
       ├→ Payment.makePayment()
       ├→ Cart.emptyCart() [if successful]
       └→ Warehouse.addItemToInventory() [if failed]

6. Invoice Generation
   └→ Invoice.generateInvoice()
```

## Design Patterns Used

### 1. **Strategy Pattern**
- **WarehouseSelectionStrategy**: Allows dynamic warehouse selection algorithms
- **PaymentMode**: Supports multiple payment methods (UPI, Credit Card, etc.)

### 2. **Facade Pattern**
- **ProductDeliverySystem**: Provides simplified interface to complex subsystems

### 3. **Controller Pattern**
- Separates business logic into dedicated controllers
- UserController, OrderController, WarehouseController

### 4. **MVC-like Architecture**
- Models: User, Order, Product, Warehouse
- Controllers: UserController, OrderController, WarehouseController
- System orchestration: ProductDeliverySystem

## Key Design Decisions

### 1. **Product Category vs Product**
- Products are grouped into categories
- Pricing is at category level (bulk pricing model)
- Allows managing multiple identical products efficiently

### 2. **Cart as Map Structure**
- Uses `Map<CategoryId, Count>` for efficient lookups
- Supports quantity updates without storing duplicate entries

### 3. **Order Immutability**
- Order captures cart state at creation time
- Prevents cart modifications from affecting placed orders

### 4. **Transaction Safety**
- Checkout process has rollback mechanism
- Inventory restored if payment fails

### 5. **Separation of Concerns**
- Clear boundaries between user, warehouse, and order domains
- Each controller manages its own entities

## Potential Enhancements

### 1. **Inventory Management**
- Stock level validation before order placement
- Low stock alerts
- Reorder point management

### 2. **Order Status Tracking**
- State machine for order lifecycle
- Status history and timestamps

### 3. **Payment Processing**
- Additional payment methods (Credit Card, Cash on Delivery)
- Payment failure handling
- Refund processing

### 4. **Advanced Warehouse Selection**
- Distance-based selection with actual coordinates
- Stock availability consideration
- Multi-warehouse order fulfillment

### 5. **User Features**
- Order history with pagination
- Wishlist functionality
- Multiple delivery addresses

### 6. **Concurrency Handling**
- Thread-safe inventory updates
- Order ID generation using UUID
- Optimistic locking for cart updates

### 7. **Search and Filtering**
- Product search by name/category
- Price range filtering
- Sorting options

## Sample Usage

```java
// Initialize system
ProductDeliverySystem system = new ProductDeliverySystem(users, warehouses);

// User browses and shops
User user = system.getUser(1);
Warehouse warehouse = system.getWarehouse(new NearestWarehouseStrategy());
Inventory inventory = system.getInventory(warehouse);

// Add to cart
system.addProductToCart(user, productCategory, 2);

// Checkout
Order order = system.placeOrder(user, warehouse);
system.checkout(order);
```

## Class Relationships Summary

- **User** → has a → **Cart** → contains → **ProductCategories**
- **Warehouse** → has a → **Inventory** → contains → **ProductCategories** → contain → **Products**
- **Order** → references → **User**, **Warehouse**, **Invoice**, **Payment**
- **Controllers** → manage → respective entities
- **ProductDeliverySystem** → coordinates → all controllers

## Conclusion

This Order Management System demonstrates solid low-level design principles with clear separation of concerns, extensibility through design patterns, and a well-structured domain model. The system is production-ready with clear enhancement paths for scaling and adding features.