package comp3607project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

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

    @Test 
    public void testJudgePortal(){
        assertNotNull(new JudgePortal());
        assertTrue((new JudgePortal()) instanceof JudgePortal);
    } 

    //General Test for Command Classes -- May require minor additions to the classes (getJudgeSystem and getFilePath)
    @Test
    public void testCommandProducePDF(){
        JudgeSystem judgeSystem = new JudgeSystem();
        String filePath;

        CommandProducePDF command = new CommandProducePDF(judgeSystem);

        //assertEquals(judgeSystem, command.getJudgeSystem());
        //assertEquals(filePath, command.getFilePath());
    }

    //Specialized Tests for Command Classes execute function -- To be Done tmr if not finished today


    //Test for Files -- No idea what to add for param for FileManager all other tests are passing
    @Test
    public void testCreateFileParser(){
        FileManager manager = new FileManager(null);
        FileIterator fileIterator = manager.createFileParser();
        assertNotNull(fileIterator,"Returned object should not be null.");
        assertTrue(fileIterator instanceof FileParser);
    }
    @Test
    public void testGetExtension(){
        FileManager manager = new FileManager(null);
        assertTrue(manager.getExtension("testFile.java"), "Method should return true");
        assertFalse(manager.getExtension("testFile.txt"), "Method should return false");
        assertFalse(manager.getExtension("testFile.jv"), "Method should return false");
    }

    @Test // Minor additions needed to FileParser(getFiles)
    public void testFileParserConstructor(){
        List<FileType> files = new ArrayList<>();
        File testFile1 = new File("test1.java");
        File testFile2 = new File("test2.java");
        files.add(new FileType(testFile1));
        files.add(new FileType(testFile2));

        //FileParser fileParser = new FileParser(files);

        //assertEquals(files, fileParser.getFiles(), "Files should match");
    }
    @Test
    public void testFileParserNext(){
        List<FileType> files = new ArrayList<>();
        File testFile1 = new File("test1.java");
        File testFile2 = new File("test2.java");
        files.add(new FileType(testFile1));
        files.add(new FileType(testFile2));

        FileParser fileParser = new FileParser(files);

        assertEquals(files.get(0), fileParser.next(), "Should return first file");
        assertEquals(files.get(1), fileParser.next(), "Should return second file");
        assertNull(fileParser.next(), "Should return Null as there are no more files");
    }
    @Test
    public void testFileParserHasNext(){
        List<FileType> files = new ArrayList<>();
        File testFile1 = new File("test1.java");
        File testFile2 = new File("test2.java");
        files.add(new FileType(testFile1));
        files.add(new FileType(testFile2));

        FileParser fileParser = new FileParser(files);

        assertTrue(fileParser.hasNext(), "Should return true before first file");
        fileParser.next();
        assertTrue(fileParser.hasNext(), "Should return true after first file");
        fileParser.next();
        assertFalse(fileParser.hasNext(), "Should return false after last file");
    }
    @Test // need to add getFile to FileType
    public void testFileTypeConstructor(){
        File file = new File("test.java");
        FileType fileType = new FileType(file);

        //assertEquals(file, fileType.getFile());
    }
    @Test
    public void testGetAbsolutePath(){
        File file = new File("test.java");
        FileType fileType = new FileType(file);

        assertEquals(file.getAbsolutePath(), fileType.getAbsolutePath());
    }
    @Test
    public void testGetName(){
        File file = new File("test.java");
        FileType fileType = new FileType(file);

        assertEquals(file.getName(), fileType.getName());
    }

    @Test
    public void shouldAnswerWithTrue() {        
        assertTrue(true);
    }
}
