/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;

import java.util.ArrayList;

public class JudgeSystem {
    
    private String uploadPath;
    private ArrayList<TestResult> summary;

    public JudgeSystem() {
        this.summary = new ArrayList<TestResult>();
    }

    public void evaluateSubmission (String filePath) {
        System.out.println ("Evaluating the submission of this file: " + filePath);
        
        //Code to do testing here
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
        // System.out.println ("Directory for pdf: " + getExtractedFilePath());
        File targetDirectory = new File (getUploadPath());

        if (!targetDirectory.exists() || !targetDirectory.isDirectory()) {
            System.out.println ("No valid directory was found!");
            return;
        } else {
            PDFGenerator.generate(uploadPath, summary);
        }
    }

    // File extractedFilesDir = new File("extracted_files");
        // File[] directories = extractedFilesDir.listFiles(File::isDirectory);
        // File targetDir = new File("");
        // if (directories != null && directories.length > 0) 
        // {
        //    targetDir = directories[0];
        // }


    public void unzipFiles(String filePath) {
        System.out.println("Unzipping the files from this file: " + filePath);

        File directory = new File("uploads");

        if (!directory.exists()) {
            directory.mkdir();
        }

        this.uploadPath = ZipFileHandler.unzip(filePath, directory);
        processUploads(directory);
    }

    private void processUploads (File directory) {
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
}
