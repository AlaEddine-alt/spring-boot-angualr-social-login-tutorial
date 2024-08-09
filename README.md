# Integrating Social Login with Angular and Spring Boot

If this repository has facilitated the integration of social login into your project, I would greatly appreciate a star ⭐ as a token of your support.
## Table of Contents
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setting up the Angular Environment](#setting-up-the-angular-environment)
4. [Configuring Spring Boot with OAuth2](#configuring-spring-boot-with-oauth2)
5. [Running the Application](#running-the-application)
6. [Troubleshooting](#troubleshooting)
7. [Contributing](#contributing)

## Introduction
This guide explains how to integrate social login functionality into an Angular frontend and a Spring Boot backend. The specific versions used in this tutorial are Angular 17.1.0, Spring Boot 3.3.2, and Spring Security 6.


For a detailed walkthrough, please refer to the [full article](https://medium.com/@oussemasahbeni300/integrating-google-social-login-with-angular-17-and-spring-boot-3-344ae8178a8d).

## Prerequisites
- Node.js and npm installed
- Angular CLI installed
- Java Development Kit (JDK) installed
- PostgreSQL database setup
- Google Console account for OAuth2 credentials

## Setting up the Angular Environment
1. **Install Angular CLI**:
    ```bash
    npm install -g @angular/cli
    ```
2. **Create a new Angular project**:
    ```bash
    ng new app-name
    ```
3. **Navigate to the project directory**:
    ```bash
    cd app-name
    ```

## Configuring Spring Boot with OAuth2
1. **Create a new Spring Boot project** using Spring Initializr or your preferred method.
2. **Add dependencies** in `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>
    ```
3. **Configure application properties** in `application.yml`:
    Make sure you registered your application in the Google Console
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/yourdatabase
        username: yourusername
        password: yourpassword
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true

    security:
      oauth2:
        client:
          registration:
            google:
              client-id: your-client-id
              client-secret: your-client-secret
              scope: profile, email
    ```

## Running the Application
1. **Start the Angular frontend**:
    ```bash
    ng serve
    ```
2. **Start the Spring Boot backend**:
    ```bash
    ./mvnw spring-boot:run
    ```
    Or use your IDE(exp: IntelliJ to start the project)

## Troubleshooting
- Ensure all dependencies are correctly installed.
- Check the console for any error messages and resolve them accordingly.
- Verify OAuth2 credentials in the Google Console.

## Contributing
Contributions are welcome! Please fork this repository and submit a pull request for any improvements.
