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
// import comp3607project.suite.ChatBotPlatformTestSuite;
// import comp3607project.suite.ChatBotSimulationTestSuite;
// import comp3607project.suite.ChatBotTestSuite;
// import comp3607project.tool.ClassHolder;
import comp3607project.tool.DynamicJavaCompiler;
import comp3607project.tool.PDFGenerator;
import comp3607project.tool.TestRunner;
import comp3607project.tool.ZipFileHandler;

import java.util.ArrayList;

public class JudgeSystem {
    
    private static String uploadPath;
    private ArrayList<TestResult> summary;

    public JudgeSystem() {
        this.summary = new ArrayList<TestResult>();
    }

    public void evaluateSubmission (String filePath) {
        System.out.println ("Evaluating the submission of this file: " + filePath);

        summary.clear();
        try {
            DynamicJavaCompiler.compile(filePath);

            TestRunner runner = new TestRunner();

            // summary = runner.run(
            //     ChatBotGeneratorTestSuite.class, 
            //     ChatBotTestSuite.class, 
            //     ChatBotPlatformTestSuite.class, 
            //     ChatBotSimulationTestSuite.class
            // );

            summary = runner.run(ChatBotGeneratorTestSuite.class);

            generateResults();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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

        setUploadPath(ZipFileHandler.unzip(filePath, directory));
        processUploads(directory);
    }

    private void processUploads(File directory) {
        FileManager fileManager = new FileManager(new File("uploads"));
        FileIterator iterator = fileManager.createFileParser();

        while  (iterator.hasNext()) {
            FileType file = iterator.next();
            setUploadPath(file.getAbsolutePath());
            evaluateSubmission(getUploadPath());
        }
        // FileManager fileManager = new FileManager(directory);
        // FileIterator iterator = fileManager.createFileParser();

        // while(iterator.hasNext()) {
        //     FileType file = iterator.next();
        //     evaluateSubmission(file.getAbsolutePath());
        // }
    }

    public static String getUploadPath() {
        return uploadPath;
    }

    public static void setUploadPath(String classPath) {
        uploadPath = classPath;
    }
}
