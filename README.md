# Cucumber-Practice
Cucumber is tool based on Behaviour Driven Development (BDD) framework which is used to write acceptance tests for the web application.It allows automation of 
functional validation in easily readable and understandable format(plain English) to Business analysts,Developers,Testers etc.

Files required to run a Cucumber test scenario:
-----------------------------------------------
Features
Step Defination file
runner file

Features
---------
keywords used are:
(a) Given -- It specifys the context of the test to be executed
(b) When -- It specifys some Test action that needs to be carried out.
(c) Then --It specifys the expected result of the Test
(d) And --It is used for additional conditions,if any

Step Defination File
-------------------
It is the actual implementation of the feature mentioned in feature file

TestRunner class File
----------------------
When Cucumber is integrated with Selenium,the starting point of execution is TestRunner class.It is recognized by @CucumberOptions which has tag which is used to 
link feature file with Stepdefination file
