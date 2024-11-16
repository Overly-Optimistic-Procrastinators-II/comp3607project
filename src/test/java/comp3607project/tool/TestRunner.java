package comp3607project.tool;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import comp3607project.TestResult;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    private ArrayList<TestResult> summary = new ArrayList<TestResult>();

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

    private void addFailures(Result result) {
        for (Failure failure : result.getFailures()) {
            summary.add(new TestResult(failure.getDescription().getMethodName(), 
                                           failure.getDescription().getDisplayName(), 
                                           "FAIL", 
                                           failure.getMessage(), 
                                           0));
        }
    }

    private void addPasses(Class<?> testClass) {
        for (Method method : testClass.getMethods()) {
            if (!isFailure(method)) {
                summary.add(new TestResult(method.getName(), 
                                            method.getName(), 
                                            "PASS", 
                                            1));
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
}


/*
 * Resources:
 * 
 * - 
 * 
 */