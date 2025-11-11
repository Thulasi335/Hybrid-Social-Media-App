# Hybrid Social Media App (Java + MySQL + MongoDB)

A mini social media application demonstrating hybrid database management using:
- **MySQL (JDBC)** for structured user & authentication data
- **MongoDB (Java Driver)** for unstructured post, likes, and comment data

## Features
- User Registration & Login (MySQL)
- Password Hashing (BCrypt)
- Create Posts, Likes, Comments (MongoDB)
- Simple Console-based Menu UI
- Clean layered structure: Model → DAO → Service → UI

## Technologies
- Java 22
- MySQL 8.0
- MongoDB 8.2
- Maven
- HikariCP Connection Pool
- BCrypt for Password Hashing
- SLF4J for Logging

## Run the App
```bash
mvn clean compile exec:java
