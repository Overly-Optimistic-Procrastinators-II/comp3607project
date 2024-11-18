/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import comp3607project.file.FileIterator;
import comp3607project.file.FileManager;
import comp3607project.file.FileType;
import comp3607project.grade.Grader;
import comp3607project.tool.DynamicClassLoader;
import comp3607project.tool.PDFGenerator;
import comp3607project.tool.FolderNameExtractor;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JudgeSystem {
    private DynamicClassLoader classLoader;
    private String uploadPath;
    private String studentinfo;
    private Grader grader;

    public JudgeSystem() {}

    public void evaluateSubmission (String filePath) {
        classLoader = new DynamicClassLoader(filePath);
        grader = new Grader(classLoader.getIsCompiled());
        
        studentinfo = FolderNameExtractor.getFolderName(filePath);
        generateResults();
    }

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

        unzipFiles(new File(filePath), topLevelDir);

        // Process nested zipped folders
        extractNestedZips(topLevelDir);
        processUploads(rootDirectory);
    }


    private static void unzipFiles(File zipFile, File destinationDir) {
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.getName().endsWith(".ctxt")) {
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
