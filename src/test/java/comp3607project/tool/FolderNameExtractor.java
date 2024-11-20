/**
 * Author: Kailash Joseph
 */

package comp3607project.tool;

import java.io.File;

public class FolderNameExtractor {
    /** Used to extract the submission name from a file path. */
    public static String getFolderName(String filePath) {
        File file = new File(filePath);

        String parentDirectory = file.getParent();
        File parentDir = new File(parentDirectory);
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