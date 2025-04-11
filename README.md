# Bookstore Management System

A web application for managing books with authentication, CRUD operations, and REST API.

## Features

- User authentication with Spring Security
- CRUD operations for books
- REST API for book management
- Filtering and pagination
- PostgreSQL database
- Docker and Docker Compose support

## Technologies

- Spring Boot 3.4.0
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Bootstrap
- Docker & Docker Compose

## Running with Docker Compose

1. Make sure you have Docker and Docker Compose installed
2. Clone the repository
3. Run the following command:

```bash
docker-compose up -d
```

4. Access the application at http://localhost:8080

## Login Credentials

- Username: `user`, Password: `password` (basic access)
- Username: `admin`, Password: `admin` (full access including API)

## REST API

- GET /api/books - Get all books with filtering and pagination
- GET /api/books/{id} - Get a specific book
- POST /api/books - Create a new book
- PUT /api/books/{id} - Update an existing book
- DELETE /api/books/{id} - Delete a book

## Running Locally

1. Make sure you have Java 21 and Maven installed
2. Clone the repository
3. Run the following command:

```bash
mvn spring-boot:run
```

4. Access the application at http://localhost:8080 