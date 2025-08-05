# 📦 API Cucumber Sample Framework

A sample test automation project for **REST API testing** using **Java** and **Behavior-Driven Development (BDD)** with **Cucumber**, focused on testing the publicly available [Zippopotam.us API](http://api.zippopotam.us).

---

## 📁 Project Structure
```
apicucumber-sample-framework/
├── 📄 README.md             # Project overview
├── 📄 pom.xml               # Maven dependencies
├── 📄 .gitignore            # Git ignored files
└── 📁 src/                  # Source code
   ├── 📁 main               # (Empty)
   └── 📁 test               # Test code and resources
      │── 📁 java 
      │        ├── 📁 runner
      │        │   └── 📄 CucumberTestRunner.java   
      │        ├── 📁 stepdefinitions
      │        │   └── 📄 LocationLookupSteps.java   # Step definitions for feature steps
      │        └── 📁 utils
      │            └── 📄 ApiUtils.java    # Reusable methods for API
      └── 📁 resources 
               ├── 📁 features             
               │   └── 📄 location-lookup.feature   
               └── 📄 log4j2.xml           # Logging configuration file
```
---

## 🚀 Getting Started

Follow these steps to set up and run the project:

---

### ✅ Prerequisites

Make sure the following tools are installed on your system:

- **Java** (I'm using Java 17)
- **Maven** (I'm using Maven 3.9)
- **IDE** –

You can verify installation by running:
```
java -version
mvn -v
```

---

### 🧪 Run the Tests

#### Option 1: From the Command Line

```bash
# navigate to the project folder
cd apicucumber-sample-framework

# run all tests
 mvn test -Dtest=CucumberTestRunner
 
# run happy cases
 mvn test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@happy" -Dcucumber.plugin=pretty
 
# run negative cases
 mvn test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@negative" -Dcucumber.plugin=pretty
```

#### Option 2: From the IDE

1. Open the file: `CucumberTestRunner.java`
2. Click the green Run button next to the class or method declaration
3. View results in the console or test output panel

## 📦 Dependencies

Key libraries used:

- [Cucumber-Java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java)
- [JUnit](https://junit.org/)
- [Maven](https://maven.apache.org/)
- [Log4j2](https://logging.apache.org/log4j/2.x/)
- Apache HttpClient

---

## 📊 Logging & Reporting

- Logs are configured via `log4j2.xml`
- Customize logging level, output file path, and pattern as needed




