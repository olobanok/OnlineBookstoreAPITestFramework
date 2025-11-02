# API Test Automation Framework (Java + Gradle + TestNG + RestAssured + Allure)

This framework is designed for testing of the FakeRestAPI Books and Authors services.

---
## 🛠 Tech Stack
| Component          | Purpose                            |
|--------------------| ---------------------------------- |
| **Java 17**        | Core language                      |
| **Gradle**         | Build and dependency management    |
| **RestAssured**    | API HTTP client                    |
| **TestNG**         | Test runner & lifecycle management |
| **Allure**         | Reporting and visualization        |
| **AssertJ**        | Fluent assertions                  |
| **Jackson**        | JSON serialization/deserialization |
| **Java Faker**     | Random test data                   |
| **GitHub Actions** | CI/CD automation                   |

## 🧱 Core Components
* API Clients (under com/example/api) - encapsulate endpoint logic, each Client method returns a ValidatableResponse.
* Specs (under com/example/spec) - reusable ResponseSpecification definitions for consistent assertions.
* Models (under com/example/model) - simple record DTOs for requests/responses.

## 🔧 Setup
**Clone**
```
git clone https://github.com/olobanok/OnlineBookstoreAPITestFramework.git
```

**Build project**

```
./gradlew clean build -x test
```

**Execute tests**

```
./gradlew test
```
**Execute tests for specific group**

```
./gradlew test -Dgroups=smoke
```
**Generate and view the report**

```
./gradlew allureReport
./gradlew allureServe
```

## 📊 Latest Allure Report is available [here](https://olobanok.github.io/OnlineBookstoreAPITestFramework/allure-report)
