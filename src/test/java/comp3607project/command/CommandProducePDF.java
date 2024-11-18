package comp3607project.command;

import comp3607project.JudgeSystem;

public class CommandProducePDF implements Command {
    private JudgeSystem judgeSystem;

    public CommandProducePDF (JudgeSystem judgeSystem) {
        this.judgeSystem = judgeSystem;
    }

    public void execute() {
        judgeSystem.generateResults();
    }
}
