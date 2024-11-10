package comp3607project;

public abstract class TestCase {
    private String id;
    private String name;
    private TestResult expectedResult;
    private TestResult actualResult;
    
    public TestCase() {

    }
    
    public abstract void initialize();
    public abstract void cleanup();
}
