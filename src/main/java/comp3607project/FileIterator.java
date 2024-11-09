/*
 * Abstract Iterator
 */

package comp3607project;

public interface FileIterator {
    public File next();
    public boolean hasNext();
    public void unzip();
}
