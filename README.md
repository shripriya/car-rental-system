# Car Rental System

## Overview
This project is a modular Car Rental System built using Java and Spring Boot. It consists of three main microservices:
- **Inventory Service**: Manages car inventory and availability.
- **Reservation Service**: Handles reservations and pricing.
- **User Service**: Manages user accounts and authentication.

Each service is independently deployable and communicates via REST APIs. The project uses Maven for build management and follows best practices for maintainability and scalability.

## Architecture
- **Microservices Architecture**: Each domain (inventory, reservation, user) is implemented as a separate Spring Boot service with its own data model, repository, service, and controller layers.
- **Layered Structure**:
  - `controller/`: REST API endpoints
  - `service/`: Business logic
  - `repository/`: Data access layer (in-memory for demo purposes)
  - `domain/`: Core domain models
  - `config/`: Configuration (e.g., OpenAPI)

## Design Patterns Used

### 1. Repository Pattern
- **Where**: `repository/` package in each service (e.g., `CarRepository`, `InMemoryCarRepository`)
- **How**: Abstracts data access logic, allowing easy swapping of data sources (e.g., in-memory, database). The service layer interacts with repositories via interfaces, promoting loose coupling.

### 2. Factory Pattern
- **Where**: `service/CarFactory.java` in Inventory Service
- **How**: Encapsulates the creation logic for `Car` objects. This centralizes object instantiation and allows for future extension (e.g., different car types).

### 3. Strategy Pattern
- **Where**: `service/PricingStrategy.java` and `service/DefaultPricingStrategy.java` in Reservation Service
- **How**: Defines a family of pricing algorithms, encapsulates each one, and makes them interchangeable. The reservation service can use different pricing strategies without modifying its code.

### 4. Command Query Responsibility Segregation (CQRS)
- **Where**: `service/ReservationCommandService.java` and `service/ReservationQueryService.java` in Reservation Service
- **How**: Separates write operations (commands) from read operations (queries), improving scalability and maintainability.

## How to Build and Run
1. Ensure Maven is installed and available in your PATH.
2. Run `mvn clean install` to build all modules and run unit tests.
3. To start a service, use:
   - `mvn spring-boot:run -pl inventory-service`
   - `mvn spring-boot:run -pl reservation-service`
   - `mvn spring-boot:run -pl user-service`

## Testing
Unit tests are provided for core service logic in each module under the `test/` directory. Run `mvn test` to execute all tests.

## API Documentation
OpenAPI (Swagger) configuration is available in each service under `config/OpenApiConfig.java` for easy API exploration.

## Notes
- All data is stored in-memory for demonstration. For production, implement persistent repositories.
- The `.gitignore` file excludes build artifacts and IDE files from version control.

---
For further details, review the source code in each service's respective package.
