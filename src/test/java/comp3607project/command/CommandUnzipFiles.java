package comp3607project.command;

import comp3607project.JudgeSystem;

public class CommandUnzipFiles implements Command {
    private JudgeSystem judgeSystem;
    private String filePath;
    
    public CommandUnzipFiles(JudgeSystem judgeSystem, String filePath) {
        this.judgeSystem = judgeSystem;
        this.filePath = filePath;
    }

    public void execute() {
        judgeSystem.unzipSubmissions(filePath);
    }
}
