# Team 15's Automated Judge System

<br>
<br>

## Introduction
<br>

### Members
* Kailash Joseph
* Amir Persad
* Jonathan Mohammed
* Tyrell Lewis
* Varun Maharaj
  

### Project Scope
1. Input/Submission Process <br>
Input: The system will accept zipped files that contain one or more Java program files. The zip file will represent the student's submission.
Validation: Ensure that the uploaded files are in the correct format and adhere to submission guidelines, e.g., correct file types and structure.

2. Evaluation <br>
Correctness: Evaluate the correctness of the Java code based on the assignment specifications.
<br> This will involve: <br>

* Test Case Evaluation: Running the student's code against predefined test cases (unit tests or other). <br>

* Error Handling: Handling exceptions, invalid inputs, or other potential issues in the student's code gracefully. <br>

* Design Evaluation: Optionally evaluate the use of object-oriented design principles (though this may be complex and require manual input or specific analysis). <br>

3. Feedback and Report Generation <br>

* Test Results: Provide detailed results of test cases, indicating whether each test passed or failed.
* Feedback: Offer corrective feedback where applicable, suggesting improvements or indicating errors.
* Score Calculation: Generate an overall score for the submission based on the test case results and feedback.
* PDF Report: Produce a PDF report that includes the test case results, feedback, and the final score.
4. System Requirements <br>

* Programming Language: The system must be implemented in Java.
* Design Patterns: Must incorporate at least three design patterns (excluding Singleton). Possible patterns could include:
- Factory Pattern: For creating objects related to different file types or report generation.
- Strategy Pattern: For handling different evaluation strategies based on assignment types.
- Observer Pattern: To notify users (e.g., the student) about the evaluation status or report availability.
* SOLID Principles: The system must adhere to SOLID principles to ensure clean, maintainable, and extendable code.
5. Packaging and Testing
* Maven Project: The solution must be packaged as a Maven project for dependency management and modularity.
* Test Suite: The system should be evaluated using a comprehensive set of test cases for different scenarios, ensuring robust evaluation of student submissions.
6. Boundaries
* Scope Limitations: The system will not manually review code for style or deeper algorithmic correctness beyond test cases.
* Automated Evaluation Only: The system evaluates the code's correctness strictly based on predefined test cases. It will not provide advanced evaluations, such as time complexity analysis unless explicitly defined as part of the test suite.
  <br>
This scope ensures the project is manageable within the given constraints while delivering a functional and efficient automated judging system for OOP1 assignments.

## Analysis
### Requirements and Use Cases
### Target Students
## Design
### Patterns Used 
### SOLID
### Class Diagram
## Implementation
### How to Run?
### Setup Requirements
## Testing and Evaluation
### Test Cases and Suites
### Demo Video


<br><br><br>

# TO BE REMOVED
  <br>
* Accepting a zipped file containing zero or more student submissions as zipped files
* Extracting and processing zero or more Java files in a student submission
* Evaluating all of the required features of the Java classes based on an assignment specification (such as class/method/attribute naming conventions and types, behaviour and functionality of methods, expected abstractions and inheritance hierarchies)
* Producing a PDF file, neatly formatted, that contains a list of all of the tests that the Java classes passed/failed together with helpful corrective feedback for the failed tests. This report file should be added to the same location as the student Java files and should contain the student ID
* Calculating an overall score for the submission using the assignment rubric and printing a breakdown of the marks per test for each class
* A Test suite for evaluation of the system’s performance

# Dependencies
* Packaged as a Maven Project

