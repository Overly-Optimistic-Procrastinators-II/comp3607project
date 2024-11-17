package comp3607project;

public class CommandProducePDF implements Command {
    private JudgeSystem judgeSystem;

    public CommandProducePDF (JudgeSystem judgeSystem) {
        this.judgeSystem = judgeSystem;
    }

    public void execute() {
        judgeSystem.generateResults();
    }
}
