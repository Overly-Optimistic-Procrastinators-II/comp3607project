/**
 * Author: Amir Persad, Kailash Joseph
 * Last Edited By: Tyrell Lewis
 */

package comp3607project.file;

import java.io.File;

/** Abstract File representations for uploaded files or directories */
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
