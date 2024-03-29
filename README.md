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
This project implements spring data rest. It just works out of the box. It provides a lot of features like pagination, sorting, filtering, etc. It also provides a lot of customization options.
For basic all we need is to create an entity and extend the CrudRepository interface. It will provide all the basic CRUD operations.

No need to create a controller, service, and repository classes. It will automatically create the endpoints for the entity.


## API Endpoints

The following API endpoints are available:

- **GET /students**: Get all students
- **GET /students/{studentId}**: Get student by ID
- **POST /students**: Add a new student
- **PUT /students/{studentId}**: Update an existing student
- **DELETE /students/{studentId}**: Delete a student by ID

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
