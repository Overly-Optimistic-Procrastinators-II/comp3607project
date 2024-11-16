/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.itextpdf.text.DocumentException;

import java.util.ArrayList;

public class JudgeSystem {
    
    private String extractedFilePath;
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
        File targetDirectory = new File (getExtractedFilePath());

        if (!targetDirectory.exists() || !targetDirectory.isDirectory()) {
            System.out.println ("No valid directory was found!");
            return;
        } else {
            PDFGenerator.generate(extractedFilePath, summary);
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
        //Code to unzip files here

        File dir = new File("extracted_files");

        if (!dir.exists()) {
            dir.mkdir();
        }

        String zipFileName = new File(filePath).getName();
        String folderName = zipFileName.substring(0, zipFileName.lastIndexOf('.'));
        File subDir = new File(dir, folderName);

        if (!subDir.exists()) {
            subDir.mkdir();
            //System.out.println("Created subdirectory: " + subDir.getAbsolutePath());
        }

        try (ZipInputStream zipInput = new ZipInputStream(Files.newInputStream(Path.of(filePath)))) {
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) 
            {
                File file = new File(subDir, entry.getName());
                if (entry.isDirectory()) 
                {
                    file.mkdirs(); 
                    // System.out.println("Created directory: " + file.getAbsolutePath());
                    System.out.println("Extracted file: " + file.getAbsolutePath());
                    
                } else {
                    if (isJavaFile(file.getName())){
                        new File(file.getParent()).mkdirs();
                        Files.copy(zipInput, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                zipInput.closeEntry();
            }
            System.out.println("Unzipping completed.");

            extractedFilePath = subDir.getAbsolutePath();
            System.out.println ("Extracted file path: " + getExtractedFilePath());
            processExtractedFiles(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processExtractedFiles (File dir) {
        FileManager fileManager = new FileManager(dir);
        FileIterator iterator = fileManager.createFileParser();

        while  (iterator.hasNext()) {
            FileType file = iterator.next();
            evaluateSubmission(file.getAbsolutePath());
        }
    }


    private boolean isJavaFile(String filePath) {
        return ((filePath.endsWith(".java") ? true : false));
    }


    public String getExtractedFilePath() {
        return extractedFilePath;
    }
}
