# REST Assured API Automation Framework

![REST Assured API Tests](https://github.com/AlfajGodad/RestAssured-API-Automation-Framework/actions/workflows/api-tests.yml/badge.svg)

A scalable REST API automation framework built using **Java, REST Assured, TestNG, Maven, Apache POI, Extent Reports, and Log4j2**.

The framework demonstrates automated CRUD testing, reusable API endpoints, POJO-based payload handling, Excel-driven testing, response validation, logging, reporting, and continuous integration using GitHub Actions.

## Tech Stack

- Java 17
- REST Assured
- TestNG
- Maven
- Jackson
- Apache POI
- Extent Reports
- Log4j2
- Git
- GitHub Actions

## API Under Test

This project uses the Swagger Petstore REST API.

Base URL:

```text
https://petstore.swagger.io/v2
```

User operations automated:

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/user` | Create user |
| GET | `/user/{username}` | Retrieve user |
| PUT | `/user/{username}` | Update user |
| DELETE | `/user/{username}` | Delete user |

## Framework Features

- CRUD API automation
- POJO-based request payloads
- Serialization using Jackson
- Status code validation
- JSON response-body validation
- Path parameter handling
- Reusable endpoint layer
- Centralized route management
- Configuration using `config.properties`
- Excel-driven testing using Apache POI
- TestNG `@DataProvider`
- Automated test-data cleanup
- Extent HTML reporting
- Log4j2 execution logging
- Maven Surefire execution
- GitHub Actions CI

## Project Structure

```text
RestAssuredAutomationFramework
│
├── .github
│   └── workflows
│       └── api-tests.yml
│
├── src/test/java
│   ├── api/endpoints
│   │   ├── Routes.java
│   │   └── UserEndpoints.java
│   │
│   ├── api/payloads
│   │   └── User.java
│   │
│   ├── api/tests
│   │   ├── UserTests.java
│   │   └── DataDrivenTests.java
│   │
│   └── api/utilities
│       ├── ConfigReader.java
│       ├── DataProviders.java
│       ├── ExcelUtility.java
│       ├── ExtentReportManager.java
│       └── ExtentReportListener.java
│
├── src/test/resources
│   ├── UserData.xlsx
│   ├── config.properties
│   └── log4j2.xml
│
├── pom.xml
├── testng.xml
└── README.md
```

## Automated Test Workflow

The main CRUD test flow is:

```text
POST
  ↓
Create User
  ↓
GET
  ↓
Validate Created User
  ↓
PUT
  ↓
Update User
  ↓
GET
  ↓
Validate Updated User
  ↓
DELETE
  ↓
GET
  ↓
Validate 404 - User Not Found
```

The data-driven workflow reads multiple users from Excel:

```text
UserData.xlsx
      ↓
ExcelUtility
      ↓
TestNG DataProvider
      ↓
User POJO
      ↓
REST Assured Endpoints
      ↓
API Validation
```

## Running the Tests

### Using Maven

From the project root:

```bash
mvn clean test
```

Maven Surefire executes the TestNG suite defined in `testng.xml`.

### Using Eclipse

Right-click:

```text
testng.xml
→ Run As
→ TestNG Suite
```

## Test Reports

Extent Reports generates an HTML execution report under:

```text
reports/
```

The report contains test execution status, passed/failed tests, execution timeline, and failure information.

## Logging

Log4j2 generates execution logs under:

```text
logs/automation.log
```

Logs include API operations, usernames, response status codes, test execution status, and failure details.

## Continuous Integration

GitHub Actions automatically executes the API automation suite for pushes and pull requests to the `main` branch.

The CI pipeline performs:

```text
Checkout Repository
        ↓
Set Up Java 17
        ↓
Restore Maven Dependencies
        ↓
mvn clean test
        ↓
Execute TestNG Suite
```

The workflow can also be triggered manually using **Run workflow** from the GitHub Actions page.

## Current Test Execution

```text
Tests Run : 9
Passed    : 9
Failed    : 0
Skipped   : 0

BUILD SUCCESS
```

## Author

**Alfaj Godad**

Software Quality Engineer / SDET  
Automation Testing | Selenium | Java | REST Assured | TestNG | API Testing