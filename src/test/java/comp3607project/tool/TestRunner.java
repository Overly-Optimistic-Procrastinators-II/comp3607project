package comp3607project.tool;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import comp3607project.TestResult;
import comp3607project.suite.TestMetaData;

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
            TestMetaData metadata = failure.getDescription().getAnnotation(TestMetaData.class);

            if (!isIgnored(getMetaDataDescription(metadata), getMetaDataMark(metadata))) {
                summary.add(new TestResult(failure.getDescription().getMethodName(), getMetaDataDescription(metadata), "FAIL", failure.getMessage(), 0));
            }
        }
    }

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


/*
 * Resources:
 * 
 * - 
 * 
 */