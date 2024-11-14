package comp3607project;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    public ArrayList<TestResult> run(Class<?>... testClasses) {
        JUnitCore core = new JUnitCore();
        ArrayList<TestResult> results = new ArrayList<TestResult>();

        for (Class<?> testClass : testClasses) {
            Result result = core.run(testClass);
            for (Failure failure : result.getFailures()) {
                results.add(new TestResult(failure.getDescription().getMethodName(), 
                                               failure.getDescription().getDisplayName(), 
                                               "FAIL", 
                                               failure.getMessage(), 
                                               0));
            }

            for (Method method : testClass.getMethods()) {
                boolean fail = false;
                for (TestResult r : results) {
                    if (r.getTestName().equals(method.getName())) {
                        fail = true;
                    }
                }

                if (!fail) {
                    results.add(new TestResult(method.getName(), 
                                                method.getName(), 
                                                "PASS", 
                                                1));
                }
            }
        }

        return results;
    }
}


/*
 * Resources:
 * 
 * - https://junit.org/junit4/javadoc/4.12/org/junit/runner/notification/RunListener.html
 * 
 */
