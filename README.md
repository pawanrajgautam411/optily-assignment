# Optily Assignment

### This application has used a list of below techs.
1. Developed using `Spring Boot` & `Spring MVC`
2. Built with `Maven`
3. Developed on `Java 8`
4. Used `Spring JPA` with `Hibernate`
5. `MySQL` database used for JPA layer

## 1. Database
### 1.1 Import Dump
Import mysql dump provided as a file `/optily_db.sql` in any mysql server.

### 1.2 Property Changes
Go to `src/main/resources/application.properties` and do the necessary changes to below properties

>`database.url=jdbc:mysql://some_host:3306/optily_db`

>`database.user=some_username`

>`database.password=some_password`

## 2. Application StartUp
### 2.1 Java version
The application is compiled and tested against `jdk1.8.0_191`

### 2.2 Maven Installation
Maven install will also be required to build the final runnable spring boot application

**NOTE:** there are no `profiles` used for simplicity, hence all the maven builds will be built with `default` profile

### 2.3 SpringBootApplication class
The startup class known as SpringBootApplication is located at `com/optily/assignment/boot/OptilyAssignmentApplication.java`

### 2.4 Run Command
Execute command `java -jar target/optily-assignment-v1.jar`

## 3. How to use APIs
### 3.1 Postman Collection
Import the postman collection provided with a name `Optily Asgmnt.postman_collection.json` in this repo
