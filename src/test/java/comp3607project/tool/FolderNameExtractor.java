package comp3607project.tool;

import java.io.File;

public class FolderNameExtractor {

    public static String getFolderName(String filePath) {
        // Create a File object
        File file = new File(filePath);

        // Get the parent directory
        String parentDirectory = file.getParent();

        // Create a File object for the parent directory
        File parentDir = new File(parentDirectory);

        // Get the name of the parent directory
        String parentDirName = parentDir.getName();

        // Split the name by underscores and remove the last part
        String[] parts = parentDirName.split("_");
        StringBuilder result = new StringBuilder();

        // Append all parts except the last one
        for (int i = 0; i < parts.length - 1; i++) {
            result.append(parts[i]);
            if (i < parts.length - 2) {
                result.append("_"); // Add underscore between parts
            }
        }

        return result.toString();
    }
}