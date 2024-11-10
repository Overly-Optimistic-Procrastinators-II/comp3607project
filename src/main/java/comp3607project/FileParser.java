/*
 * Concrete Iterator
 */

package comp3607project;

import java.util.List;

public class FileParser implements FileIterator {
    private List<File> files;
    private int pos = 0;

    public FileParser(List<File> files) {
        this.files = files;
    }

    public File next() {
        return files.get(pos++);
    }

    public boolean hasNext() {
        return pos < files.size();
    }
}
