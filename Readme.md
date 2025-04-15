## Pre-Requisite

Following libraries should be added in pom.xml

1. Java 8 & above
2. TestNG - 6.14.3
3. Selenium-Java - 3.141.59
4. Extent Report - 4.0.0
5. Maven


   
# About Framework:
- Framework is designed as Page Object model where all page locators are placed in their respective pages.
- Each Tests are independent with each other. Can change to tests priority to any order to execute the Test Suite.
- All tests have an assertion to confirm success/failure during execution
- Framework support to execute in CI/CD pipeline
- Easily can execute in different browsers by modifying changes in property file

# How To Execute Tests:
- In Terminal, navigate to project path and execute tests using mvn test -DsuiteFile=Testexecution.xml

# Execution Flow:
- When execute this command "mvn clean install test -DsuiteXmlFile=Testexecution.xml", it triggers the TestNG Xml file where the xml file path is configured in Maven surfire-plugin
- TestNG xml file will execute tests which are configured.

- Report includes success test and failed tests with screenshots.
![Test Failure](/images/TestSuccess.png)
![Test Success](/images/TestFailure.png)

