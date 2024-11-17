/*
 * Concrete Aggregate
 */

package comp3607project.file;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class FileManager implements FileContainer {
    private List<FileType> files;

    public FileManager (File directory) {
        files = new ArrayList<FileType>();
        addDirectories(directory);
    }

    private void addDirectories(File directory) {
        if (directory.isDirectory()) {
            File [] fileList = directory.listFiles();
            boolean hasJavaFiles = false;
            
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile() && getExtension(file.getName())) {
                        hasJavaFiles = true;
                    }
                    else if (file.isDirectory()) {
                        addDirectories(file);
                    }
                }
            }

            if (hasJavaFiles || (fileList != null && fileList.length == 0)) {
                files.add(new FileType(directory));
            }
        }
    }

    public FileIterator createFileParser() {
        return new FileParser(files);
    }

    public boolean getExtension(String filePath) {
        return (filePath.endsWith(".java") ? true : false);
    }
}
