/*
 * Concrete Iterator
 */

package comp3607project;

import java.util.List;

public class TestRunner implements TestIterator {
    private List<TestCase> tests;
    private int pos = 0;

    public TestRunner(List<TestCase> tests) {
        this.tests = tests;
    }

    public TestCase next() {
        return new TestCase();
    }

    public boolean hasNext() {
        return pos < tests.size();
    }
}
