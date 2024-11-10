/*
 * Concrete Aggregate
 */

package comp3607project;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager implements FileContainer {
    private List<FileType> files;

    public FileManager (File dir)
    {
        files = new ArrayList<FileType>();
    }

    public FileIterator createFileParser() {
        return new FileParser(files);
    }

    
}
