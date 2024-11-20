/**
 * Author: Tyrell Lewis
 * 
 * Command Design Pattern
 * Receiver
 * Carries out the requests of the commands
 * 
 * Facade Design Pattern
 * Facade
 * Acts as a facade for the various subsytems:
 * File Management, Zip Handling, Grading and PDF Generation
 */

package comp3607project;

import java.io.File;

import comp3607project.file.FileIterator;
import comp3607project.file.FileManager;
import comp3607project.file.FileType;
import comp3607project.grade.Grader;
import comp3607project.tool.DynamicClassLoader;
import comp3607project.tool.PDFGenerator;
import comp3607project.tool.ZipFileHandler;
import comp3607project.tool.FolderNameExtractor;


public class JudgeSystem {
    private DynamicClassLoader classLoader;
    private String uploadPath;
    private String studentinfo;
    private Grader grader;

    public JudgeSystem() {}
    
    /**
     * Last Edited By: Jonathan Mohammed
     * 
     * 
     */
    public void evaluateSubmission (String filePath) {
        classLoader = new DynamicClassLoader(filePath);
        grader = new Grader(classLoader.getIsCompiled());
        
        studentinfo = FolderNameExtractor.getFolderName(filePath);
        generateResults();
    }

    /**
     * Last Edited By: Jonathan Mohammed
     * 
     * 
     */
    public void generateResults() {
        try{
            File targetDirectory = new File (getUploadPath());

            if (!targetDirectory.exists() || !targetDirectory.isDirectory()) {
                System.out.println ("No valid directory was found!");
                return;
            } else {
                PDFGenerator.generate(getUploadPath(), grader.getResults(), studentinfo, grader.getGrade());
            }
        } catch (Exception e) {
            System.err.println("Could not create PDF");
        }
    }

    /**
     * Last Edited By: Kailash Joseph
     */
    public void unzipSubmissions(String filePath) {
        // System.out.println("Unzipping submissions.zip and its nested zipped folders from: " + filePath);

        File rootDirectory = new File("uploads");
        if (!rootDirectory.exists()) {
            rootDirectory.mkdir();
        }

        // Unzip the top-level submissions.zip
        File topLevelDir = new File(rootDirectory, "submissions");
        if (!topLevelDir.exists()) {
            topLevelDir.mkdir();
        }

        ZipFileHandler.unzip(filePath, topLevelDir);

        processUploads(rootDirectory);
    }

    /**
     * Last Edited By: Jonathan Mohammed
     */
    private void processUploads(File directory) {
        FileManager fileManager = new FileManager(directory);
        FileIterator iterator = fileManager.createFileParser();

        while (iterator.hasNext()) {
            FileType file = iterator.next();
            setUploadPath(file.getAbsolutePath());
            evaluateSubmission(getUploadPath());
        }
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String filePath) {
        uploadPath = filePath;
    }
}
