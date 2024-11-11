package comp3607project;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

public class CommandProducePDF implements Command
{
    private JudgeSystem judgeSystem;
    private String filePath;

    public CommandProducePDF (JudgeSystem judgeSystem)//, String filePath)
    {
        this.judgeSystem = judgeSystem;
        //this.filePath = filePath;
    }

    public void execute()
    {
        try 
        {
            judgeSystem.generateResults();//filePath);
        } 
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (DocumentException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
