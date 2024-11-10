/*
 * Concrete Aggregate
 */

package comp3607project;

import java.util.List;

public class FileManager implements FileContainer {
    private List<FileType> files;

    public FileManager() {

    }

    public FileIterator createFileParser() {
        return new FileParser(files);
    }
}
