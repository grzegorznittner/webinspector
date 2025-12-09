# Web Inspector

A small Spring Boot web application with a simple UI for inspecting and experimenting with web resources.  
The project demonstrates a typical Spring MVC + HTML/CSS front‑end setup packaged as a standalone Spring Boot application.

---

## Features

- **Spring Boot backend**
  - Auto-configured embedded server (Tomcat/Jetty/Undertow depending on setup)
  - Simple controller layer returning views
  - Conventional configuration via `application.properties`

- **Simple UI**
  - Single-page style HTML front-end
  - Modern, responsive layout (CSS only, no heavy JS framework required)
  - Ready to extend with your own forms, inputs, and result panels

- **Developer-friendly**
  - Run with a single command
  - Hot reload support when used with Spring DevTools
  - Easy to package as a runnable JAR

---

## Prerequisites

- Java 17+
- Maven or Gradle (depending on your build tool of choice)
- An IDE such as IntelliJ IDEA (recommended)

---

## Getting Started

### 1. Clone the repository

bash git clone [https://example.com/your-org/webinspector.git](https://example.com/your-org/webinspector.git)
cd webinspector

### 2. Configuration

In order to build or run the app you need OpenAI API key, you need to set environment variable: OPENAPI_KEY
Contact me to get the test key.


### 3. Build the project

**Gradle:**

bash ./gradlew clean build

**IntelliJ:**

Import project from existing sources, select gradle as build tool.


### 4. Run the application

bash ./gradlew bootRun

**Or run the packaged JAR:**

bash java -jar target/webinspector-0.0.1-SNAPSHOT.jar

**IntelliJ:**

You can run the app in IntelliJ, the main class is WebInspectorApplication
Don't forget to set environment variable OPENAPI_KEY, you can do that via Edit Configuration


### 5. Access the application

Go to http://localhost:8080




