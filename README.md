# E-Commerce Microservices Project

## 🌟 Overview
This project is a modern e-commerce platform built using a microservices architecture. It demonstrates the implementation of various microservices patterns and best practices, providing a scalable and maintainable solution for e-commerce operations.

## 🏗️ Architecture
The system is composed of multiple microservices, each responsible for specific business capabilities:

- **Customer Service**: Manages customer data and authentication
- **Product Service**: Handles product catalog and inventory
- **Order Service**: Processes and manages orders
- **Payment Service**: Handles payment processing
- **Notification Service**: Manages communication with users
- **Gateway Service**: API Gateway for routing and load balancing
- **Config Server**: Centralized configuration management
- **Discovery Service**: Service registration and discovery

## 🛠️ Technologies

### Core Technologies
- **Spring Boot**: Framework for creating microservices
- **Spring Cloud**: For cloud-native application development
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data persistence
- **Spring Cloud Gateway**: API Gateway implementation

### Databases
- **PostgreSQL**: Primary relational database
- **MongoDB**: NoSQL database for specific services
- **PgAdmin & Mongo Express**: Database management tools

### Message Broker
- **Apache Kafka**: Event-driven communication between services
- **Zookeeper**: Distributed configuration and synchronization

### Security & Authentication
- **Keycloak**: Identity and access management
- **JWT**: Token-based authentication

### Monitoring & Tracing
- **Zipkin**: Distributed tracing system
- **Spring Actuator**: Application monitoring and metrics

### Development Tools
- **Docker**: Containerization
- **Docker Compose**: Container orchestration
- **MailDev**: Email testing service

## 🚀 Getting Started

### Prerequisites
- Docker and Docker Compose
- Java 17 or higher
- Maven

### Running the Application

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Start the infrastructure services:
```bash
docker-compose up -d
```

3. The following services will be available:
- PostgreSQL: localhost:5432
- PgAdmin: localhost:5050
- MongoDB: localhost:27017
- Mongo Express: localhost:8081
- Kafka: localhost:9092
- Zipkin: localhost:9411
- Keycloak: localhost:9098
- MailDev: localhost:1080 (UI), localhost:1025 (SMTP)

## 🔌 Service Ports
- Gateway Service: 8080
- Config Server: 8888
- Discovery Service: 8761
- Customer Service: 8081
- Product Service: 8082
- Order Service: 8083
- Payment Service: 8084
- Notification Service: 8085

## 🌐 Network Architecture
All services communicate through a dedicated Docker network (`microservices-net`) and are discoverable via the service registry.

## 📊 Data Flow
1. Requests come through the API Gateway
2. Gateway authenticates requests using Keycloak
3. Requests are routed to appropriate microservices
4. Services communicate through both REST and event-driven patterns (Kafka)
5. Distributed tracing is handled by Zipkin

## 🔐 Security
- Keycloak handles authentication and authorization
- API Gateway secures all incoming requests
- Inter-service communication is secured
- Sensitive data is encrypted

## 📝 Logging and Monitoring
- Centralized logging system
- Distributed tracing with Zipkin
- Health checks via Spring Actuator
- Performance metrics collection

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details. 
