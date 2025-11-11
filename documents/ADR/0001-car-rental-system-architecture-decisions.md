# 1. Record architecture decisions

Date: 2025-11-11

## Status

Proposed

## Context

We are tasked with designing and implementing a simulated car rental system that must support car inventory management, reservations and availability tracking. The system should be scalable, maintainable, and allow independent deployment of components. The system must:
- Allow users to reserve a car (Sedan, SUV, Van) for a specific date and time, for a set number of days.
- Limit the number of cars available per type.
- Automatically update car availability after reservation changes.

**Bounded Contexts and Domains:**
1. **Inventory Context**
   - Manages car catalog, car types, features and tracks stock availability and maintenance status.
   - Exposes REST APIs to add, update, delete and view car inventory.
2. **Reservation Context**
   - Handles reservation creation, modification, scheduling (date, time, duration), validation (availability, conflicts) and cancellation.
   - Exposes REST APIs for CRUD operations.
3. **User Context**
   - Exposes REST APIs for CRUS operations of User data

**Testing**
- Unit and integration tests for all REST APIs to verify correctness and requirements.

## Decision

We will implement the system using a microservices architecture. Each bounded context (Car Inventory, Reservation and User Management) will be a separate Spring Boot service. Service will communicate via REST APIs.

### Tech stack chosen
Java 17, Spring Boot 3.1, Spring Cloud

### Architecture
- **Microservices Architecture**: Each domain (inventory, reservation, user) is implemented as a separate Spring Boot service with its own data model, repository, service, and controller layers.
- **Layered Structure**:
  - `controller/`: REST API endpoints
  - `service/`: Business logic
  - `repository/`: Data access layer (in-memory for demo purposes)
  - `domain/`: Core domain models
  - `config/`: Configuration (e.g., OpenAPI)

### Design Patterns Used

#### 1. Repository Pattern
- **Where**: `repository/` package in each service (e.g., `CarRepository`, `InMemoryCarRepository`)
- **How**: Abstracts data access logic, allowing easy swapping of data sources (e.g., in-memory, database). The service layer interacts with repositories via interfaces, promoting loose coupling.

#### 2. Factory Pattern
- **Where**: `service/CarFactory.java` in Inventory Service
- **How**: Encapsulates the creation logic for `Car` objects. This centralizes object instantiation and allows for future extension (e.g., different car types).

#### 3. Strategy Pattern
- **Where**: `service/PricingStrategy.java` and `service/DefaultPricingStrategy.java` in Reservation Service
- **How**: Defines a family of pricing algorithms, encapsulates each one, and makes them interchangeable. The reservation service can use different pricing strategies without modifying its code.

#### 4. Command Query Responsibility Segregation (CQRS)
- **Where**: `service/ReservationCommandService.java` and `service/ReservationQueryService.java` in Reservation Service
- **How**: Separates write operations (commands) from read operations (queries), improving scalability and maintainability.

### Diagrams


![Car Rental System - Requirements](/documents/ADR/images/Requirements.png)

![Class Diagram](/documents/ADR/images/UML-class.jpg)

![Sequence Diagram](/documents/ADR/images/UML-sequence.jpg)

![ER Diagram](/documents/ADR/images/ER.jpg)


## Consequences

- **Modular Development:** Each bounded context is implemented as a separate microservice, enabling independent development, testing and deployment.
- **Scalability:** The architecture supports scaling individual services based on demand.
- **Maintainability:** clear separation of concents makes the codebase easier to maintain and extend.
- **Technology Flexibility:** Each service can evolve independently, allowing future migrations to storage solutions
- **Operational Complexity:** Service orchestration, inter-service communication, and data consistency require careful management.

## References
- Project prompt and requirements
- DDD and microservices best practices
- ADR template and status conventions
