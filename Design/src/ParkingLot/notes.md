# Low-Level Design: Parking Lot System

## Requirements

1. Configure multiple parking floors with different types of spots:
2. Handicapped, Compact, Large, Motorcycle.
3. Allow four types of vehicles to park: Car, Truck, Van, Motorcycle.
5. Generate and assign parking tickets upon vehicle entry.
6. Calculate parking fees based on duration.
7. Accept payments via: Credit/Debit Card, Cash.
9. Prevent vehicle entry if the parking lot reaches its maximum capacity.
10. Display available parking spots dynamically on the display board.
11. Notify users if the parking lot is full.
12. Allow admins to manage floors, spots, and configurations.
---
## Design Patterns

* Singleton Pattern - Ensures only one ParkingLot instance.
* Factory Pattern - Creates ParkingSpot and Vehicle objects.
* Observer Pattern - Updates DisplayBoard based on parking changes.
* Strategy Pattern - Handles payment and parking fee calculations dynamically.
---
## Design Principles Applied
* Single Responsibility: Separate classes for different functionalities.
* Open/Closed Principle: Extend functionality without modifying existing code.
* Liskov Substitution: Vehicle subtypes can be used interchangeably.
* Interface Segregation: Focused interfaces for specific functionalities.
* Dependency Inversion: High-level modules depend on abstractions.
* Encapsulation: Hide internal details and expose only necessary APIs.
* Thread Safety: Prevent race conditions with synchronized operations.
---

## Parking Lot System - Design Patterns and Principles

                             +---------------------+
                             |   ParkingLot        |
                             |---------------------|
                             | - instance          |
                             | - floors            |
                             | - entrancePoints    |
                             | - exitPoints        |
                             |---------------------|
                             | + getInstance()     |
                             | + parkVehicle()     |
                             | + removeVehicle()   |
                             +---------------------+
                                       |
                                       |
                      +----------------+----------------+
                      |                                 |
             +-------------------+               +-----------------+
             | ParkingFloor       |               | EntrancePoint   |
             |-------------------|               |-----------------|
             | - floorNumber      |               | - id            |
             | - spots            |               |-----------------|
             |-------------------|               | + issueTicket() |
             | + addVehicle()     |               +-----------------+
             | + removeVehicle()  |
             +-------------------+
                      |
                      |
        +---------------------------+
        |        ParkingSpot         |
        |---------------------------|
        | - number                   |
        | - isFree                   |
        | - vehicle                  |
        |---------------------------|
        | + assignVehicle()          |
        | + removeVehicle()          |
        +---------------------------+
                      |
          +-------------------------+
          |  <<Factory>>             |
          | ParkingSpotFactory       |
          +-------------------------+
                      |
                      |
          +-------------------------+
          |       Vehicle            |
          |-------------------------|
          | - licenseNumber          |
          | - vehicleType            |
          |-------------------------|
          | + getType()              |
          +-------------------------+
                      |
                      |
          +-------------------------+
          |  <<Factory>>             |
          |  VehicleFactory          |
          +-------------------------+
                      |
        +-------------------+
        |    Car             |
        +-------------------+
        |    Truck           |
        +-------------------+
        |    Motorcycle      |
        +-------------------+
        
          +-----------------------+
          |   <<Observer>>         |
          |   DisplayBoard         |
          +-----------------------+
          | - availableSpots       |
          +-----------------------+
          | + update()             |
          +-----------------------+

          +-----------------------+
          |   PaymentStrategy      |
          +-----------------------+
          | + processPayment()     |
          +-----------------------+
                    |
        +-------------------------+
        |  CreditCardPayment       |
        |-------------------------|
        | + processPayment()       |
        +-------------------------+
        |  CashPayment             |
        |-------------------------|
        | + processPayment()       |
        +-------------------------+
