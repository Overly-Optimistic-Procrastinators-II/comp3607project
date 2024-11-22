/**
 * Author: Jonathan Mohammed
 */

package comp3607project.tool;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import comp3607project.grade.TestResult;
import comp3607project.suite.TestMetaData;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    private ArrayList<TestResult> summary = new ArrayList<TestResult>(); //**Stores the test results so that it can be outputted to the PDF */

    public TestRunner() {}

    public ArrayList<TestResult> run(Class<?>... testClasses) {
        JUnitCore core = new JUnitCore();

        for (Class<?> testClass : testClasses) {
            Result result = core.run(testClass);
            addFailures(result);
            addPasses(testClass);
        }

        return summary;
    }


    /**
     * Processes the failures from the JUnit result and adds them 
     * to the summary list. Iterates through test failures and checks 
     * metadata annotations, adding relevant information to the TestResult.
     * @param result
     */
    private void addFailures(Result result) {
        for (Failure failure : result.getFailures()) {
            TestMetaData metadata = failure.getDescription().getAnnotation(TestMetaData.class);
            
            if (!isIgnored(getMetaDataDescription(metadata), getMetaDataMark(metadata))) {
                summary.add(new TestResult(failure.getDescription().getMethodName(), getMetaDataDescription(metadata), "FAIL", failure.getMessage(), 0));
            } else {
                summary.add(new TestResult(failure.getDescription().getMethodName(), failure.getDescription().getMethodName(), "FAIL", failure.getMessage(), 0));
            }
        }
    }

    /**
     * Identifies the tests that have passed and adds their details to 
     * the summary list. Uses java reflection to iterate over methods in class.
     * Checks their annotation for metadata and determines if considered a pass
     * @param testClass
     */
    private void addPasses(Class<?> testClass) {
        for (Method method : testClass.getMethods()) {
            TestMetaData metadata = method.getAnnotation(TestMetaData.class);

            if (!isFailure(method) && !isIgnored(getMetaDataDescription(metadata), getMetaDataMark(metadata))) {
                summary.add(new TestResult(method.getName(), getMetaDataDescription(metadata), "PASS", getMetaDataMark(metadata)));
            }
        }
    }

    private boolean isFailure(Method method) {
        for (TestResult r : summary) {
            if (r.getTestName().equals(method.getName())) {
                return true;
            }
        }

        return false;
    }

    private boolean isIgnored(String description, int mark) {
        return ((description.equals("skip") || mark == -1) ? true : false);
    }

    private String getMetaDataDescription(TestMetaData metadata) {
        return (metadata != null) ? metadata.description() : "skip";
    }

    private int getMetaDataMark(TestMetaData metadata) {
        return (metadata != null) ? Integer.parseInt(metadata.marks()) : -1;
    }
}


/**
 * Resources:
 * 
 * - https://stackoverflow.com/questions/2543912/how-do-i-run-junit-tests-from-inside-my-java-application
 * - https://junit.org/junit4/javadoc/4.12/org/junit/runner/notification/Failure.html
 * - https://junit.org/junit4/javadoc/4.13/org/junit/runner/Description.html
 * - https://junit.org/junit4/javadoc/4.12/org/junit/runner/notification
 * - https://junit.org/junit4/javadoc/latest/org/junit/runner/Result.html
 * - https://stackoverflow.com/questions/67185821/collect-junit-test-metadata-via-annotations
 * 
 */
