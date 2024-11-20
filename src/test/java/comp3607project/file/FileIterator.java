/**
 * Author: Kailash Joseph
 * 
 * Iterator Design Pattern
 * Abstract Iterator
 * Declares the operations that are applied to the list in the FileManager
 */

package comp3607project.file;

public interface FileIterator {
    public FileType next();
    public boolean hasNext();
}
