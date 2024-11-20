/**
 * Author: Jonathan Mohammed
 */

package comp3607project.grade;

import java.util.ArrayList;

import comp3607project.suite.ChatBotGeneratorTestSuite;
import comp3607project.suite.ChatBotPlatformTestSuite;
import comp3607project.suite.ChatBotSimulationTestSuite;
import comp3607project.suite.ChatBotTestSuite;
import comp3607project.tool.TestRunner;

public class Grader {
    private boolean isCompiled;
    private int grade;
    private ArrayList<TestResult> results;

    public Grader(boolean isCompiled) {
        this.isCompiled = isCompiled;
        runTests();
        calculateGrade();
    }

    public ArrayList<TestResult> getResults() {
        return results;
    }

    public int getGrade() {
        return grade; 
    }

    public boolean getIsCompiled() {
        return isCompiled;
    }

    private void calculateGrade() {
        for(TestResult r : results) {
            grade += r.getMark();
        }

        if (isCompiled) {
            grade += 15;
        }
    }

    private void runTests() {
        try {
            TestRunner runner = new TestRunner();

            results = runner.run(
                ChatBotGeneratorTestSuite.class, 
                ChatBotTestSuite.class, 
                ChatBotPlatformTestSuite.class, 
                ChatBotSimulationTestSuite.class
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
