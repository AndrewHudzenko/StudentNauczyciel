# ****📚Student & Nauczyciel App Project📚**** #

## 🚀 Description
This is a simple version of the Student & Nauczyciel App written on Spring Boot. \
You can send various HTTP requests(GET, POST, PUT, DELETE) \
to add or receive some info. \

## 🚀 Project structure
**The project has an N-Tier Architecture**
- Controller - This level allows the user to work with this application.
- Service - This level of architecture is responsible for processing the data received from the repositories level.
- Repository - This level of architecture is responsible for communicating with the database provided by Spring Boot.
- H2 database.

## 🚀 Features
- CRUD operations with teacher and student
- Get student by teacher and student by teacher
- Get operations include pagination and sorting
- Find teacher or student by firstName and lastName
- add & remove student to/from teacher and teacher to/from student

## 🚀 Technologies
- Java 17
- Spring Boot
- Spring WEB
- Maven
- H2
- Tomcat
- Hibernate
- Spring Web/Security
- Stream API
- REST
- Lombok

## 🚀 Quickstart
1. Fork this repository
2. Copy link of project
3. Create new project from Version Control
4. Edit resources/db.properties - set the necessary parameters
``` java
    db.driver=YOUR_DRIVER
    db.url=YOUR_URL
    db.user=YOUR_USERNAME
    db.password=YOUR_PASSWORD
    ...
```
5Run project

## 🚀 Example of parameters for db.properties
``` java
    spring.datasource.url=jdbc:h2:mem:test
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```