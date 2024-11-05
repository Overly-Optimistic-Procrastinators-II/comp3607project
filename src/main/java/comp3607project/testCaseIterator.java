package comp3607project;

import java.util.List;

public class testCaseIterator implements testIterator
{

    private List<testCase> tests;
    private int pos = 0;

    public testCaseIterator(List<testCase> tests)
    {
        this.tests = tests;
    }

    public testCase next()
    {
        testCase temp = new testCase();
        return temp;
    }

    public boolean hasNext()
    {
        return pos < tests.size();
    }

    public void print()
    {

    }
}
