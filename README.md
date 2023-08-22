# QA Automation Diploma Project

---

## About

Java automation project covering functional testing, GUI and API testing. The project was created using the TestiNG
framework and the Page Object Model.

The Allure Report system is used as the main reporting system.


---

## Tested Website:

<a href="https://qase.io/">
<img src="https://d36r73waboa44k.cloudfront.net/2023/01/qase-logo-blog.png"  height="100" /> </a>

Qase is an all-in-one TestOps platform built to beautifully orchestrate QA for both manual & automated testing. With
Qase, QA teams can orchestrate test case management, defect management, test plans, test runs with informative reports
and also utilize a rich API and webhooks. Qase has integrations with the most popular issue trackers including Jira,
Asana, Slack, and dozens more.


---

## Technology:

* Java
* Maven

### Tools & Frameworks:

* Selenium - Library (version 4.9.1)
* TestNG - Framework (version 7.8.0)
* Allure Reports - Reporting System (version 2.21.0)
* Lombok - Library for shortening code in classes and extending the functionality of the Java (version 1.18.26)
* JavaFaker - a library that allows to generate random data (version 1.0.2)
* GSON - library for serializing and deserializing Java objects to JSON (version 2.10.1)
* Log4j - Java logging library (version 2.20.0)
* REST Assured - Java library for testing RESTful APIs (version 5.3.1)
* Listeners - interface for Logs & Customizable TestNG Reports

---


# Checklist:   <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-2zxrPS-jxOUT69kT4EX_KT8EhdK8mbqYVA&usqp=CAU"  height="50" /> </a>

* Create new Project (Positive & negative tests)
* Create new Test Case 
* Suite (create new suite, edit suite, delete suite)
* Create new test case
* Login tests (positive & negative)








## API Tests: <img src="https://static.vecteezy.com/system/resources/previews/021/730/337/non_2x/api-icon-vector.jpg"  height="70" /> </a>

* **Defect**                            
    * Create a new defect.
    * Get a specific defect.
    * Update defect.
    * Delete defect.
* **Test Case**
    * Create a new test case.
    * Get a specific test case.
    * Update test case.
    * Delete test case.

## Patterns Used: 

* Page Object
* Element Decorators 
* Value Object
* Builder 
* Loadable Page 

# Test running and reporting:
Instructions for running tests:

Smoke Tests: mvn clean test -DsuiteXmlFile = "smokeTests.xml"
Regression Tests: mvn clean test -DsuiteXmlFile = "regressionTests.xml"
Smoke API Tests: mvn clean test -DsuiteXmlFile = "smokeApiTests.xml"




    



    
