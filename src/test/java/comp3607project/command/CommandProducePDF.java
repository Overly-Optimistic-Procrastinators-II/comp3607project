/**
 * Author: Tyrell Lewis
 * 
 * Command Design Pattern
 * Concrete Command that handles the results generation by assigning the operation to the judgeSystem
 */

package comp3607project.command;

import comp3607project.JudgeSystem;

public class CommandProducePDF implements Command {
    private JudgeSystem judgeSystem;/** Creates a JudgeSystem attribute to call generate results*/

    public CommandProducePDF (JudgeSystem judgeSystem) {
        this.judgeSystem = judgeSystem;
    }

    public void execute() {
        judgeSystem.generateResults();
    }
}
