/*
 * Concrete Iterator
 */
//pulling

package comp3607project.file;

import java.util.List;

public class FileParser implements FileIterator {
    private List<FileType> files;
    private int pos = 0;

    public FileParser(List<FileType> files) {
        this.files = files;
    }

    public FileType next() {
        if (hasNext())
            return files.get(pos++);
        return null;
    }

    public boolean hasNext() {
        return pos < files.size();
    }
}
