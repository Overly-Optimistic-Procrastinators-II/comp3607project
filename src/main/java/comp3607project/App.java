package comp3607project;

public class App 
{
    public static void main(String[] args) 
    {
        
        JudgeSystem judgeSystem = new JudgeSystem();
        
        JudgePortal autoJudgePortal = new JudgePortal(judgeSystem);
        
        autoJudgePortal.setVisible(true);
    }
}


