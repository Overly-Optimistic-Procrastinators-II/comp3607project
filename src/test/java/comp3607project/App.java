/**
 * Author: Tyrell Lewis
 * 
 * Creates the view layer (JudgePortal) and associates it with a controller (JudgeSystem)
 */

package comp3607project;

public class App {
    public static void main(String[] args) {
        JudgeSystem judgeSystem = new JudgeSystem();
        JudgePortal autoJudgePortal = new JudgePortal(judgeSystem);
        autoJudgePortal.setVisible(true);
    }
}
