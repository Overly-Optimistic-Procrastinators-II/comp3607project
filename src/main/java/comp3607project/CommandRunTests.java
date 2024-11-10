/*
 * Concrete Command
 */

package comp3607project;

public class CommandRunTests implements Command 
{

    private JudgeSystem judgeSystem;
    private String filePath;
    
    public CommandRunTests(JudgeSystem judgeSystem, String filePath) 
    {
        this.judgeSystem = judgeSystem;
        this.filePath = filePath;
    }

    public void execute()
    {
        judgeSystem.evaluateSubmission(filePath);
    }
}
