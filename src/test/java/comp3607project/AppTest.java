package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test 
    public void testJudgeSystem(){
        assertNotNull(new JudgeSystem());
        assertTrue((new JudgeSystem()) instanceof JudgeSystem);
    }
    // Tests for App
    @Test 
    public void testJudgePortal(){
        assertNotNull(new JudgePortal);
        assertEquals((new JudgePortal()) instanceof JudgePortal);
    } 

    // General Test for Command Classes -- May require minor additions to the classes (getJudgeSystem and getFilePath)
    @Test
    public void testCommandProducePDF(){
        private JudgeSystem judgeSystem;
        private String filePath;

        CommandProducePDF command = new CommandProducePDF(judgeSystem, filePath);

        assertEquals(judgeSystem, command.getJudgeSystem());
        assertEquals(filePath, command.getFilePath());
    }

    // Specialized Tests for Command Classes execute function -- To be Done tmr if not finished today


    //

    @Test
    public void shouldAnswerWithTrue() {        
        assertTrue( true );
    }
}
