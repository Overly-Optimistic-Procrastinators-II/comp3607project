/**
 * Author: Amir Persad
 * 
 * Iterator Design Pattern
 * Abstract Aggregate
 * Defines an interface which declares a method to create a FileParser iterator object
 */

package comp3607project.file;

public interface FileContainer {
    FileIterator createFileParser();
}
