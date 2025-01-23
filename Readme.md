# User Provisioning API

This is a Spring Boot application for managing users and their roles.

## Features
- Create, update, delete users and roles.
- Assign roles to users.
- Search and filter users by name or email.
- Pagination support for user and role listing.

## Prerequisites
- Java 17 or later
- Maven 3.8+
- MySQL 

## Setup Instructions
1. Extract the ZIP file.
2. Import as existing maven  project in Eclipse or IntelliJ.  
3.Update the application.properties file with your database configuration:

properties:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

4.Run the application as Java Application in IDE.

5.The application will start on `http://localhost:8080`.

6.Import the provided Postman collection to test the APIs.