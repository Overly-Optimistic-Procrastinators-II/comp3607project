/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import comp3607project.file.FileIterator;
import comp3607project.file.FileManager;
import comp3607project.file.FileType;
import comp3607project.suite.ChatBotGeneratorTestSuite;
import comp3607project.suite.ChatBotPlatformTestSuite;
import comp3607project.suite.ChatBotSimulationTestSuite;
import comp3607project.suite.ChatBotTestSuite;
import comp3607project.tool.DynamicJavaCompiler;
import comp3607project.tool.PDFGenerator;
import comp3607project.tool.TestRunner;
import comp3607project.tool.ZipFileHandler;

import java.util.ArrayList;

public class JudgeSystem {   
    private static String uploadPath;
    private ArrayList<TestResult> summary;
    private static int grade;
    private static boolean isCompiled;
    private static URLClassLoader classLoader;

    public JudgeSystem() {
        this.summary = new ArrayList<TestResult>();
    }

    public void evaluateSubmission (String filePath) {
        System.out.println ("Evaluating the submission of this file: " + filePath);

        summary.clear();
        grade = 0;
        isCompiled = compileSubmission(filePath);
        
        try {
            loadClasses(filePath);

            TestRunner runner = new TestRunner();

            summary = runner.run(
                ChatBotGeneratorTestSuite.class, 
                ChatBotTestSuite.class, 
                ChatBotPlatformTestSuite.class, 
                ChatBotSimulationTestSuite.class
            );

            generateResults();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        unloadClasses();
    }

    public void generateResults() {
        try{
            System.out.println ("Directory for pdf: " + getUploadPath());
            File targetDirectory = new File (getUploadPath());

            if (!targetDirectory.exists() || !targetDirectory.isDirectory()) {
                System.out.println ("No valid directory was found!");
                return;
            } else {
                calculateMark();
                PDFGenerator.generate(getUploadPath(), summary);
            }
        } catch (Exception e) {
            System.err.println("Could not create PDF");
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
    }

    private boolean compileSubmission(String filePath) {
        try {
            DynamicJavaCompiler.compile(filePath);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void loadClasses(String filePath) {
        try {
            classLoader = URLClassLoader.newInstance(new URL[] {
                new File(filePath).toURI().toURL()
            });
        } catch (Exception e) {
            System.out.println("Could not load classes");
        }
    }

    private void unloadClasses() {
        if (classLoader != null) {
            try {
                classLoader.close();
            } catch (IOException e) {
                System.err.println("Could not close class loader");
                e.printStackTrace();
            }
        }
    }

    public static String getUploadPath() {
        return uploadPath;
    }

    public static void setUploadPath(String classPath) {
        uploadPath = classPath;
    }

    private void calculateMark() {
        for(TestResult s : summary) {
            grade += s.getMark();
        }

        if (isCompiled) {
            grade += 15;
        }
    }

    public static int getGrade() {
        return grade;
    }
}
