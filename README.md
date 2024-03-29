# Student Management REST API

This project implements a RESTful API for managing student resources, including CRUD operations. It is built using Spring Boot.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Setup Instructions](#setup-instructions)
- [Development](#development)
- [Technologies Used](#technologies-used)

## Introduction

The Student Management REST API provides endpoints to perform CRUD operations on student resources. It includes functionalities to retrieve all students, get a student by ID, add a new student, update an existing student, and delete a student.

## Project Structure

The project follows the standard Spring Boot structure:

- `src/main/java`: Contains Java source code
   - `com.murariwalake.springboot.controller`: Controller classes handling HTTP requests
   - `com.murariwalake.springboot.model`: Model classes representing student data
   - `com.murariwalake.springboot.service`: Service classes containing business logic
- `src/main/resources`: Contains application properties and configuration files
- `pom.xml`: Maven project configuration file

## API Endpoints

The following API endpoints are available:

- **GET /api/v1/students**: Get all students
- **GET /api/v1/students/{studentId}**: Get student by ID
- **POST /api/v1/students**: Add a new student
- **PUT /api/v1/students/{studentId}**: Update an existing student
- **DELETE /api/v1/students/{studentId}**: Delete a student by ID

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 17
- MySQL running on port 3306 with a database named `student_db`

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/murariwalake/spring-boot-crud-demo.git
   cd spring-boot-crud-demo
2. **Build and run the application**:

   ```bash
   ./mvnw clean install
   java -jar target/spring-boot-crud-demo-0.0.1-SNAPSHOT.jar
   ```
3. **Access the API**:

   Use [Student_API2.postman_collection.json](./Student_API2.postman_collection.json) to import the postman collection and test the API.
