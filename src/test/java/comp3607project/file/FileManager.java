/*
 * Concrete Aggregate
 */

package comp3607project.file;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class FileManager implements FileContainer {
    private List<FileType> files;

    public FileManager (File dir) {
        files = new ArrayList<FileType>();
        addingJavaFilesFromDirectory(dir);
    }

    private void addingJavaFilesFromDirectory(File dir) {
        if (dir.isDirectory()) {
            File [] fileList = dir.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile() && getExtension(file.getName())) {
                        files.add(new FileType(file));
                    }
                    else if (file.isDirectory()) {
                        addingJavaFilesFromDirectory(file);
                    }
                }
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
