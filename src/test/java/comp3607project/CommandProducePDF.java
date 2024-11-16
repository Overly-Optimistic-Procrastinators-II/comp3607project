package comp3607project;

import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;

public class CommandProducePDF implements Command {
    private JudgeSystem judgeSystem;

    public CommandProducePDF (JudgeSystem judgeSystem) {
        this.judgeSystem = judgeSystem;
    }

    public void execute() {
        try {
            judgeSystem.generateResults();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
