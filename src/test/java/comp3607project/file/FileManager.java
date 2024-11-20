/*
 * Author: Amir Persad, Kailash Joseph
 * 
 * Iterator Design Pattern
 * Concrete Aggregate
 * Stores a list of directories representing submission folders
 * Creates a concrete iterator (FileParser) to traverse the list of directories
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

    /**
     * Last Edited By: Jonathan Mohammed
     * 
     * Add Directories of submissions to the list of abstract files to be processed
     */
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
