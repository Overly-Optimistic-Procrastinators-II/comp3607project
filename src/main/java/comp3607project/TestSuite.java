/*
 * Concrete Aggregate
 */

package comp3607project;

import java.util.ArrayList;

public class TestSuite implements TestContainer {
    private ArrayList<TestCase> tests;

    private TestSuite() {
        this.tests = new ArrayList<TestCase>();
        // tests.add(new TestChatBot());
        // tests.add(new TestChatBotPlatform());
        // tests.add(new TestChatBotGenerator());
        // tests.add(new TestChatBotSimulation());
    }

    public TestIterator createTestRunner() {
        return new TestRunner(tests);
    }

    public static void run(){

    }

}
