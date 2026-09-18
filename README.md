# REST Assured API Automation Framework

![REST Assured API Tests](https://github.com/AlfajGodad/RestAssured-API-Automation-Framework/actions/workflows/api-tests.yml/badge.svg)

A REST API automation framework built using **Java, REST Assured, TestNG, Maven, Apache POI, Extent Reports, and Log4j2**.

The framework demonstrates automated CRUD testing, positive and negative API validation, reusable endpoint design, POJO-based payload handling, Excel-driven testing, logging, reporting, and continuous integration using **GitHub Actions**.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| REST Assured | REST API automation |
| TestNG | Test execution and assertions |
| Maven | Build and dependency management |
| Jackson | POJO serialization/deserialization support |
| Apache POI | Excel test-data handling |
| Extent Reports | HTML test reporting |
| Log4j2 | Framework logging |
| Git | Version control |
| GitHub | Source-code repository |
| GitHub Actions | Continuous Integration |

---

## API Under Test

The framework uses the **Swagger Petstore REST API** for demonstrating API automation.

Base URL:

```text
https://petstore.swagger.io/v2
```

### Automated User Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/user` | Create a new user |
| GET | `/user/{username}` | Retrieve user details |
| PUT | `/user/{username}` | Update an existing user |
| DELETE | `/user/{username}` | Delete an existing user |

---

## Framework Features

- REST API automation using REST Assured
- GET, POST, PUT and DELETE request automation
- Complete CRUD user lifecycle testing
- Positive and negative API scenarios
- HTTP status-code validation
- JSON response-body validation
- Path parameter handling
- POJO-based request payloads
- Object serialization using Jackson
- Reusable API endpoint methods
- Centralized route management
- Configuration management using `config.properties`
- Excel-driven test execution
- Apache POI Excel utility
- TestNG `@DataProvider`
- Multiple test-data execution
- Automated test-data cleanup
- Extent HTML reports
- TestNG `ITestListener`
- ThreadLocal-based Extent test handling
- Log4j2 execution logging
- Maven Surefire execution
- Command-line test execution
- GitHub version control
- GitHub Actions CI
- Automatic CI execution on push and pull request
- Manual CI execution using `workflow_dispatch`
- Extent Report upload as GitHub Actions artifact
- Execution log upload as GitHub Actions artifact

---

## Project Structure

```text
RestAssuredAutomationFramework
│
├── .github
│   └── workflows
│       └── api-tests.yml
│
├── src
│   └── test
│       │
│       ├── java
│       │   └── api
│       │       │
│       │       ├── endpoints
│       │       │   ├── Routes.java
│       │       │   └── UserEndpoints.java
│       │       │
│       │       ├── payloads
│       │       │   └── User.java
│       │       │
│       │       ├── tests
│       │       │   ├── UserTests.java
│       │       │   ├── DataDrivenTests.java
│       │       │   └── NegativeUserTests.java
│       │       │
│       │       └── utilities
│       │           ├── ConfigReader.java
│       │           ├── DataProviders.java
│       │           ├── ExcelUtility.java
│       │           ├── ExtentReportManager.java
│       │           └── ExtentReportListener.java
│       │
│       └── resources
│           ├── UserData.xlsx
│           ├── config.properties
│           └── log4j2.xml
│
├── .gitignore
├── pom.xml
├── testng.xml
└── README.md
```

---

## Framework Architecture

The framework separates API endpoints, payloads, tests, test data, configuration, and utilities into independent layers.

```text
Test Classes
     │
     ▼
UserEndpoints
     │
     ▼
REST Assured
     │
     ▼
Swagger Petstore API
     │
     ▼
Response
     │
     ▼
Assertions / Validation
```

Supporting components:

```text
Excel Test Data ──► DataProvider ──► Tests

config.properties ──► ConfigReader ──► Framework

TestNG ──► ExtentReportListener ──► Extent HTML Report

Test Execution ──► Log4j2 ──► automation.log
```

---

## CRUD Test Workflow

The main user lifecycle covers the complete CRUD flow:

```text
POST /user
     │
     ▼
Create User
     │
     ▼
GET /user/{username}
     │
     ▼
Validate Created User
     │
     ▼
PUT /user/{username}
     │
     ▼
Update User
     │
     ▼
GET /user/{username}
     │
     ▼
Validate Updated User
     │
     ▼
DELETE /user/{username}
     │
     ▼
Delete User
     │
     ▼
GET /user/{username}
     │
     ▼
Validate HTTP 404
```

The final `404` validation confirms that the deleted user can no longer be retrieved.

---

## Data-Driven Testing

Test data is stored in:

```text
src/test/resources/UserData.xlsx
```

The framework uses:

```text
UserData.xlsx
      │
      ▼
ExcelUtility
      │
      ▼
TestNG DataProvider
      │
      ▼
DataDrivenTests
      │
      ▼
User POJO
      │
      ▼
REST Assured Endpoints
```

This allows multiple users to be tested without hard-coding test data inside the test classes.

Each Excel dataset executes the required API lifecycle and validations independently.

---

## Test Coverage

The framework currently executes **12 automated API tests** covering CRUD, data-driven, positive, and negative scenarios.

### Positive and CRUD Scenarios

- Create a new user using POST
- Validate successful user creation
- Retrieve created user using GET
- Validate user response data
- Update an existing user using PUT
- Retrieve and validate updated user
- Delete an existing user using DELETE
- Verify deleted user returns HTTP `404`

### Data-Driven Scenarios

Multiple user datasets are read dynamically from Excel using **Apache POI** and **TestNG DataProvider**.

The data-driven flow performs:

```text
Read Excel Data
      ↓
Create User
      ↓
Retrieve / Validate User
      ↓
Delete User
      ↓
Verify User Deletion
```

### Negative Scenarios

The suite also validates error handling:

- GET request for a non-existing user → HTTP `404`
- DELETE request for a non-existing user → HTTP `404`
- GET request with an invalid username → HTTP `404`

---

## Running the Tests

### Prerequisites

Make sure the following are installed:

- Java 17 or later
- Maven
- Git

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

### Run Using Maven

Open a terminal from the project root and execute:

```bash
mvn clean test
```

Maven Surefire automatically executes the TestNG suite configured in:

```text
testng.xml
```

Expected result:

```text
Tests run: 12
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

### Run Using Eclipse

Right-click:

```text
testng.xml
```

Then select:

```text
Run As
→ TestNG Suite
```

---

## Extent Reports

The framework generates an HTML report after test execution using **Extent Reports**.

Reports are generated under:

```text
reports/
```

Example:

```text
reports/API_Test_Report_yyyy.MM.dd.HH.mm.ss.html
```

The report provides:

- Test execution summary
- Passed tests
- Failed tests
- Skipped tests
- Execution timeline
- Failure details and stack traces
- Individual test execution status

Excel-driven executions are identified separately in the report using their test parameters.

Example:

```text
testUserLifecycle [user201]
testUserLifecycle [user202]
testUserLifecycle [user203]
```

---

## Log4j2 Logging

The framework uses **Log4j2** for execution and debugging logs.

Logs are generated under:

```text
logs/automation.log
```

Example log output:

```text
[INFO] ExtentReportListener - Starting test: testCreateUser
[INFO] UserEndpoints - Sending POST request to create user
[INFO] UserEndpoints - POST completed | Status Code: 200
[INFO] ExtentReportListener - Test passed: testCreateUser
```

API operations such as POST, GET, PUT and DELETE are logged along with response status codes.

---

## Maven Surefire

The project uses the **Maven Surefire Plugin** to execute the TestNG suite from Maven.

Execution:

```bash
mvn clean test
```

Flow:

```text
Maven
   ↓
Surefire Plugin
   ↓
testng.xml
   ↓
TestNG
   ↓
REST Assured Tests
```

This allows the same tests to run locally and in Continuous Integration environments.

---

## Continuous Integration — GitHub Actions

The project is integrated with **GitHub Actions**.

Workflow:

```text
.github/workflows/api-tests.yml
```

The pipeline automatically runs when code is:

- Pushed to `main`
- Submitted through a pull request to `main`
- Manually triggered using **Run workflow**

### CI Pipeline

```text
Code Push / Pull Request
          ↓
GitHub Actions
          ↓
Ubuntu Runner
          ↓
Checkout Repository
          ↓
Set Up Java 17
          ↓
Restore Maven Dependencies
          ↓
mvn clean test
          ↓
Execute 12 API Tests
          ↓
Generate Extent Report
          ↓
Generate Log4j2 Logs
          ↓
Upload CI Artifacts
```

---

## GitHub Actions Artifacts

After execution, GitHub Actions preserves the generated framework outputs as artifacts.

### Extent Report Artifact

```text
extent-api-test-report
```

Contains the generated HTML Extent Report.

### Execution Logs Artifact

```text
api-test-logs
```

Contains the Log4j2 execution logs.

Artifacts are uploaded using:

```text
if: always()
```

Therefore, reports and logs can still be collected when a test fails, helping with CI failure analysis.

---

## Current Test Execution

```text
Total Tests : 12
Passed      : 12
Failed      : 0
Skipped     : 0

BUILD SUCCESS
```

The complete suite runs successfully through:

```text
Eclipse / TestNG
        ✓

Maven CLI
        ✓

GitHub Actions
        ✓
```

---

## Key Learning Demonstrated

This project demonstrates practical experience with:

- REST API testing
- REST Assured automation
- API CRUD operations
- Request and response validation
- JSON handling
- POJO serialization
- TestNG test design
- Data-driven testing
- Excel integration
- Reusable automation framework design
- Logging and reporting
- Maven build execution
- Git version control
- Continuous Integration
- CI artifact management
- Positive and negative API testing

---

## Repository

This repository contains the complete source code, test data, Maven configuration, TestNG configuration, logging configuration, and GitHub Actions CI workflow for the API automation framework.

---

## Author

**Alfaj Godad**

Software Quality Engineer / SDET

**Skills:** Selenium | Java | REST Assured | TestNG | API Testing | Maven | Git | GitHub Actions