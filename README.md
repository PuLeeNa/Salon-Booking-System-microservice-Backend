# Salon Booking System - Microservices Backend

A comprehensive salon booking system built using microservices architecture with Spring Boot and PostgreSQL.

## 🏗️ Architecture Overview

This project follows a microservices architecture pattern with six independent services, each managing specific business domains.

## 📋 Services

| Service | Port | Database | Description |
|---------|------|----------|-------------|
| **User Service** | 5001 | userdb | Manages user accounts, authentication, and profiles |
| **Salon Service** | 5002 | salondb | Handles salon information and management |
| **Category Service** | 5003 | categorydb | Manages service categories and classifications |
| **Service Offering** | 5004 | servicesdb | Manages available salon services and offerings |
| **Booking Service** | 5005 | bookingdb | Handles appointment bookings and scheduling |
| **Payment Service** | 5006 | paymentdb | Processes payments via Stripe integration |

## 🛠️ Technology Stack

- **Framework:** Spring Boot 4.0.x
- **Java Version:** 17
- **Database:** PostgreSQL
- **ORM:** Hibernate/JPA
- **Payment Gateway:** Stripe
- **Build Tool:** Maven
- **Additional:** Lombok, Spring DevTools, Validation

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Stripe API Key (for payment service)

### Database Setup

Create the following PostgreSQL databases:

```sql
CREATE DATABASE userdb;
CREATE DATABASE salondb;
CREATE DATABASE categorydb;
CREATE DATABASE servicesdb;
CREATE DATABASE bookingdb;
CREATE DATABASE paymentdb;
```

### Configuration

1. **Database Configuration:**
   Update `application.properties` in each service with your PostgreSQL credentials:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

2. **Payment Service Configuration:**
   Add your Stripe API key in `payment-service/src/main/resources/application.properties`:
   ```properties
   stripe.api.key=your_stripe_secret_key
   app.frontend.url=http://localhost:3000
   ```

### Running the Services

Navigate to each service directory and run:

```bash
# For Windows
mvnw.cmd spring-boot:run

# For Linux/Mac
./mvnw spring-boot:run
```

Or run each service individually:

```bash
cd user-service && mvnw.cmd spring-boot:run
cd salon-service && mvnw.cmd spring-boot:run
cd category-service && mvnw.cmd spring-boot:run
cd service-offering && mvnw.cmd spring-boot:run
cd booking-service && mvnw.cmd spring-boot:run
cd payment-service && mvnw.cmd spring-boot:run
```

## 🔌 API Endpoints

Each service runs independently on its designated port:

- User Service: `http://localhost:5001`
- Salon Service: `http://localhost:5002`
- Category Service: `http://localhost:5003`
- Service Offering: `http://localhost:5004`
- Booking Service: `http://localhost:5005`
- Payment Service: `http://localhost:5006`

## 💳 Payment Integration

The Payment Service integrates with Stripe for secure payment processing:

- **Success Redirect:** `/payment-success/{orderId}`
- **Cancel Redirect:** `/payment/cancel`
- **Currency:** LKR (Sri Lankan Rupee)
- **Payment Methods:** Card payments via Stripe Checkout

## 📦 Project Structure

```
backend (microservices)/
├── user-service/
├── salon-service/
├── category-service/
├── service-offering/
├── booking-service/
├── payment-service/
└── README.md
```

Each service follows standard Spring Boot project structure:
```
service-name/
├── src/
│   ├── main/
│   │   ├── java/com/puLeeNa/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── mvnw / mvnw.cmd
```

## 📝 Notes

- Ensure all databases are created before starting the services
- Services can be run independently or simultaneously
- PostgreSQL must be running on default port 5432
- Frontend application should run on port 3000 for payment redirects