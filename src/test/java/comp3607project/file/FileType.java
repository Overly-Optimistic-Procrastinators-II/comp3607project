package comp3607project.file;

import java.io.File;

public class FileType {
    private File file;

    public FileType(File file) {
        this.file = file;
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public String getName() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }
}
