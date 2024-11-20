/**
 * Author: Jonathan Mohammed
 */

package comp3607project.grade;

public class TestResult {
    private String testName;
    private String description;
    private String status;
    private String comment;
    private int mark;

    public TestResult(String testName, String description, String status, int mark) {
        this.testName = testName;
        this.description = description;
        this.status = status;
        this.mark = mark;
        this.comment = "";
    }

    public TestResult(String testName, String description, String status, String comment, int mark) {
        this.testName = testName;
        this.description = description;
        this.status = status;
        this.comment = comment;
        this.mark = mark;
    }

    public String getTestName() {
        return this.testName;
    }

    public String getDescription() {
        return this.description;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public int getMark() {
        return this.mark;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String toString() {
        return ("[" + this.getStatus() + "] [" + this.getMark() + " mark] " + 
                this.getDescription() + " " + this.getComment() + "\n");
    }
}
