package comp3607project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.zip.ZipOutputStream;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {        
        assertTrue( true );
    }

    @Test 
    public void testJudgeSystem(){
        assertNotNull(new JudgeSystem());
        assertTrue((new JudgeSystem()) instanceof JudgeSystem);
    }

    @Test 
    public void testJudgePortal(){
        assertNotNull(new JudgePortal());
        assertTrue((new JudgePortal()) instanceof JudgePortal);
    } 

    // General Test for Command Classes -- May require minor additions to the classes (getJudgeSystem and getFilePath)
    // @Test
    // public void testCommandProducePDF(){
    //     JudgeSystem judgeSystem;
    //     String filePath;

    //     CommandProducePDF command = new CommandProducePDF(judgeSystem, filePath);

    //     assertEquals(judgeSystem, command.getJudgeSystem());
    //     assertEquals(filePath, command.getFilePath());
    // }

    // Specialized Tests for Command Classes execute function -- To be Done tmr if not finished today
}
