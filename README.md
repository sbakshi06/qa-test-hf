# UI-test-framework-selenium
Automation test suite for [Automation practice website]

### Summary

This framework is designed to achieve automated UI testing for [Automation practice website].

The following flows are automated:

> Validation of registering a new user on the website.

> Validation of successful login of an existing user on the website.

> Validation of placing an order successfully by an existing user on the website.


### Technologies used

* TestNG for test driven development approach.
* Selenium for Web Automation.

A modular approach is followed with method and variable names suggesting their purpose throughout the project, to avoid redundant comments.

# How to run tests:
Import the project as a Maven project and execute following command.
```sh
$ mvn test
```
The **testng report** can be found under the generated **target** folder at the following path:
```
./target/surefire-reports/html/index.html
```
### How to generate TestNG reports manually:
```
Run the testng.xml file
```
The **testng report** is generated under **test-output** folder at path **./test-output/html/index.html**. A sample test run is present at the same location for reference.

### Screenshot Utility!
The framework supports screenshot generation at test failure.
The **.png** screenshot file will be generated under **./screenshots** folder.

**NOTE :** A default set of parameters are configured under **./src/test/resources/config.properties** file, please alter these parameters before test execution if required.

[Automation practice website]: https://www.automationpractice.com/index.php

  
