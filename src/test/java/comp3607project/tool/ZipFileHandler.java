/**
 * Author: Kailash Joseph
 */

package comp3607project.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileHandler {

    public static void unzip(String filePath, File topLevelDir) {
        unzipFiles(new File(filePath), topLevelDir);
        extractNestedZips(topLevelDir);
    }

    /**
     * Extracts files from a given ZIP file to a specified directory
     * skipping over the .class files. Uses Java's ZipFile to read ZIP file
     * and handle directory creation and file copying
     * @param zipFile
     * @param destinationDir
     */
    private static void unzipFiles(File zipFile, File destinationDir) {
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.getName().endsWith(".class")) {
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


    /**
     * Processes a directory recursively to extract all .zip files including
     * those nested within subdirectories. Ensures that each .zip file
     * is unzipped into corresponding directory and removes original .zip
     * file after extraction.
     * @param directory
     */
    private static void extractNestedZips(File directory) {
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
}

/*
 * References:
 * 
 * - https://www.geeksforgeeks.org/how-to-zip-and-unzip-files-in-java/
 * 
 */