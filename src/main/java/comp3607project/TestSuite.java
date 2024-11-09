/*
 * Concrete Aggregate
 */

package comp3607project;

import java.util.List;

public class TestSuite implements TestContainer {
    private List<TestCase> tests;

    private TestSuite() {

    }

    public TestIterator createTestRunner() {
        return new TestRunner(tests);
    }
}
