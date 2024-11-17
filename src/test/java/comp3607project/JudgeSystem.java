/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import comp3607project.file.FileIterator;
import comp3607project.file.FileManager;
import comp3607project.file.FileType;
import comp3607project.suite.ChatBotGeneratorTestSuite;
import comp3607project.suite.ChatBotPlatformTestSuite;
import comp3607project.suite.ChatBotSimulationTestSuite;
import comp3607project.suite.ChatBotTestSuite;
import comp3607project.tool.DynamicJavaCompiler;
import comp3607project.tool.ExtractFolderName;
import comp3607project.tool.PDFGenerator;
import comp3607project.tool.TestRunner;
import comp3607project.tool.ZipFileHandler;
import comp3607project.tool.ExtractFolderName;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class JudgeSystem {   
    private static String uploadPath;
    private ArrayList<TestResult> summary;
    private static int grade;
    private static boolean isCompiled;
    private static URLClassLoader classLoader;
    private String folderName;
    public JudgeSystem() {
        this.summary = new ArrayList<TestResult>();
    }

    public void evaluateSubmission (String filePath) {
        System.out.println ("Evaluating the submission of this file: " + filePath);


        System.out.println("FilePath is: " + filePath + "\n");
        folderName = "";
        folderName = ExtractFolderName.getFolderName(filePath);

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
                PDFGenerator.generate(getUploadPath(), summary, folderName);
            }
        } catch (Exception e) {
            System.err.println("Could not create PDF");
        }
    }

    public void unzipSubmissions(String filePath) {
        System.out.println("Unzipping submissions.zip and its nested zipped folders from: " + filePath);

        File rootDirectory = new File("uploads");
        if (!rootDirectory.exists()) {
            rootDirectory.mkdir();
        }

        // Unzip the top-level submissions.zip
        File topLevelDir = new File(rootDirectory, "submissions");
        if (!topLevelDir.exists()) {
            topLevelDir.mkdir();
        }

        unzipFiles(new File(filePath), topLevelDir);

        // Process nested zipped folders
        extractNestedZips(topLevelDir);
        processUploads(rootDirectory);
    }


    private static void unzipFiles(File zipFile, File destinationDir) 
    {
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.getName().endsWith(".ctxt")) {
                    System.out.println("Skipping .ctxt file: " + entry.getName());
                    continue;
                }

                File entryDestination = new File(destinationDir, entry.getName());
                if (entry.isDirectory()) {
                    entryDestination.mkdirs();
                } else {
                    entryDestination.getParentFile().mkdirs();
                    Files.copy(zip.getInputStream(entry), entryDestination.toPath());
                }
            }
        } catch (IOException e) {
            System.err.println("Error unzipping file: " + zipFile.getName() + " - " + e.getMessage());
        }
        
    }

    private void extractNestedZips(File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively process subdirectories
                extractNestedZips(file);
            } else if (file.getName().endsWith(".zip")) {
                // Unzip nested zipped folder
                File nestedDir = new File(file.getParentFile(), file.getName().replace(".zip", ""));
                nestedDir.mkdir();
                unzipFiles(file, nestedDir);
                file.delete(); // Optionally remove the nested zip after extraction
            }
        }
    }



    // public void unzipFiles(String filePath) {
    //     System.out.println("Unzipping the files from this file: " + filePath);


    //     File directory = new File("uploads");
    //     if (!directory.exists()) {
    //         directory.mkdir();
    //     }

    //     setUploadPath(ZipFileHandler.unzip(filePath, directory));
    //     processUploads(directory);
    // }

    // private void processUploads(File directory) {
    //     FileManager fileManager = new FileManager(new File("uploads"));
    //     FileIterator iterator = fileManager.createFileParser();

    //     while  (iterator.hasNext()) {
    //         FileType file = iterator.next();
    //         setUploadPath(file.getAbsolutePath());
    //         evaluateSubmission(getUploadPath());
    //     }
    // }

    private void processUploads(File directory) {
        FileManager fileManager = new FileManager(directory);
        FileIterator iterator = fileManager.createFileParser();

        while (iterator.hasNext()) {
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
