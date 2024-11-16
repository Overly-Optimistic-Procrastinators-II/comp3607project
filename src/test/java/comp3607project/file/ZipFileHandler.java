package comp3607project.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileHandler {
    private static ZipEntry zipEntry;
    private static File subDirectory;

    public static String unzip(String filePath, File targetDirectory) {
        createTargetSubDirectory(filePath, targetDirectory);
        unzipFiles(filePath);
        return subDirectory.getAbsolutePath();
    }

    private static void createTargetSubDirectory(String filePath, File targetDirectory) {
        String zipFileName = new File(filePath).getName();
        String directory = zipFileName.substring(0, zipFileName.lastIndexOf('.'));
        subDirectory = new File(targetDirectory, directory);

        if (!subDirectory.exists()) {
            subDirectory.mkdir();
        }
    }

    private static void unzipFiles(String filePath) {
        try (ZipInputStream zipInput = new ZipInputStream(Files.newInputStream(Path.of(filePath)))) {
            while((zipEntry = zipInput.getNextEntry()) != null) {
                File file = new File(subDirectory, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    file.mkdirs();
                    System.out.println("Extracted file: " + file.getAbsolutePath());
                } else {
                    if (isJavaFile(file.getName())) {
                        new File(file.getParent()).mkdirs();
                        Files.copy(zipInput, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }

                zipInput.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isJavaFile(String filePath) {
        return ((filePath.endsWith(".java") ? true : false));
    }
}

/*
 * References:
 * 
 * - https://www.geeksforgeeks.org/how-to-zip-and-unzip-files-in-java/
 * 
 */