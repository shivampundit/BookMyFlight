This project is developed for learning purposes and references are taken from our Trainer JBossRamana Material. For doubt and improvement Google, Youtube, and Stack Overflow are referred.

# BookMyFlight, Flight Booking App
A Spring Boot Microservice application developed using Eureka Server, Apache Kafka, Circuit Breaker (REsilience4j), Validation, LoginController(AOP), ExceptionHandling, Hateous, Swagger, and more.

## Microservices Architecture

### Booking Microservice
The Booking Microservice handles flight booking operations. It includes the following components:
- Aspect: LoggingAspect for capturing logs
- Controller: Exposes REST endpoints for booking flights
- Dao: Handles interaction with the database (MySQL)
- Exception: Custom exception handling
- Model: Defines the Booking entity
- Service: Implements business logic for flight booking
- Kafka: Integrates with Kafka to notify Payment Service

### Payment Microservice
The Payment Microservice manages payment processing for flight bookings. It consists of:
- Aspect: LoggingAspect for capturing logs
- Controller: Exposes REST endpoints for payment processing
- Dao: Manages payment data in the database (MySQL)
- Exception: Custom exception handling
- Model: Defines the Payment entity
- Service: Implements payment logic and communicates with Booking Microservice
- Kafka: Consumes notifications from the Booking Service 

## Why We Chose This Architecture
- **Microservices Architecture:** We adopted a microservices architecture to achieve modularity, scalability, and maintainability. both microservices focus on a specific domain, making it easier to develop and deploy.
- **Eureka for Service Discovery:** Eureka enables dynamic service discovery. It ensures that the Booking and Payment microservices can locate each other effortlessly, even in a distributed environment.
- **Kafka for Asynchronous Communication:** Kafka enables asynchronous communication between microservices, improving performance and reliability. It allows us to decouple booking and payment processes, enhancing scalability.
- **H2 Database:** We use H2 to store booking and payment data.

## Getting Started
To run the Flight Booking App locally, follow these steps:

1. Clone the repository: `git clone https://github.com/shivampundit/BookMyFlight.git`
2. Navigate to the project directory: `cd BookMyFlight` AND then start Eureka Server.
3. Start the Booking Microservice: `cd BookingService && mvn spring-boot:run`
4. Start the Payment Microservice: `cd PaymentService && mvn spring-boot:run`
5. Visit http://localhost:8080 to access the application.

Feel free to explore each microservices for detailed API endpoints and usage.
For any questions or feedback, contact shivatiwari1648@gmail.com

Happy flying with the BookiMyFlight Flight Booking App!
