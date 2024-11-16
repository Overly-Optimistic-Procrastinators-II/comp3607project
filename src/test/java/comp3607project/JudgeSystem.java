/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;

import comp3607project.file.FileIterator;
import comp3607project.file.FileManager;
import comp3607project.file.FileType;
import comp3607project.suite.ChatBotGeneratorTestSuite;
import comp3607project.suite.ChatBotPlatformTestSuite;
import comp3607project.suite.ChatBotSimulationTestSuite;
import comp3607project.suite.ChatBotTestSuite;
import comp3607project.tool.PDFGenerator;
import comp3607project.tool.TestRunner;
import comp3607project.tool.ZipFileHandler;

import java.util.ArrayList;

public class JudgeSystem {
    
    private String uploadPath;
    private ArrayList<TestResult> summary;

    public JudgeSystem() {
        this.summary = new ArrayList<TestResult>();
    }

    public void evaluateSubmission (String filePath) {
        System.out.println ("Evaluating the submission of this file: " + filePath);

        summary.clear();
        TestRunner runner = new TestRunner();

        summary = runner.run(
            ChatBotGeneratorTestSuite.class, 
            ChatBotTestSuite.class, 
            ChatBotPlatformTestSuite.class, 
            ChatBotSimulationTestSuite.class
        );
    }

    public void generateResults() throws DocumentException, FileNotFoundException {   
        System.out.println ("Directory for pdf: " + getUploadPath());
        File targetDirectory = new File (getUploadPath());

        if (!targetDirectory.exists() || !targetDirectory.isDirectory()) {
            System.out.println ("No valid directory was found!");
            return;
        } else {
            PDFGenerator.generate(getUploadPath(), summary);
        }
    }

    public void unzipFiles(String filePath) {
        System.out.println("Unzipping the files from this file: " + filePath);

        File directory = new File("uploads");

        if (!directory.exists()) {
            directory.mkdir();
        }

        this.uploadPath = ZipFileHandler.unzip(filePath, directory);
        processUploads(); //(directory);
    }

    private void processUploads () { // (File directory) {
        // FileManager fileManager;
        // FileIterator iterator;

        // File extractedFilesDir = new File("extracted_files");
        // File[] directories = extractedFilesDir.listFiles(File::isDirectory);
        // File targetDir = new File("");
        // if (directories != null && directories.length > 0) 
        // {
        //    targetDir = directories[0];
        // }
        File directory = new File("uploads");
        FileManager fileManager = new FileManager(directory);
        FileIterator iterator = fileManager.createFileParser();

        while  (iterator.hasNext()) {
            FileType file = iterator.next();
            evaluateSubmission(file.getAbsolutePath());
        }
    }

    public String getUploadPath() {
        return this.uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
