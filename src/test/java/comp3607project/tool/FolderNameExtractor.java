/**
 * Author: Kailash Joseph
 */

package comp3607project.tool;

import java.io.File;



/**
 * Used to extract the submission name from a file path.
 */
public class FolderNameExtractor {

    public static String getFolderName(String filePath) {
        File file = new File(filePath);

        String parentDirectory = file.getParent();
        File parentDir = new File(parentDirectory);
        String parentDirName = parentDir.getName();

        String[] parts = parentDirName.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < parts.length - 1; i++) {
            result.append(parts[i]);
            if (i < parts.length - 2) {
                result.append("_");
            }
        }

        return result.toString();
    }
}