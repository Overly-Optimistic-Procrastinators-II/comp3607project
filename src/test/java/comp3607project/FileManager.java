/*
 * Concrete Aggregate
 */

package comp3607project;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.StandardCopyOption;
// import java.util.zip.ZipEntry;
// import java.util.zip.ZipInputStream;

public class FileManager implements FileContainer {
    private List<FileType> files;

    public FileManager (File dir)
    {
        files = new ArrayList<FileType>();
        addingJavaFilesFromDirectory(dir);
    }

    private void addingJavaFilesFromDirectory(File dir)
    {
        if (dir.isDirectory())
        {
            File [] fileList = dir.listFiles();
            if (fileList != null)
            {
                for (File file : fileList)
                {
                    if (file.isFile() && getExtension(file.getName()))
                    {
                        files.add(new FileType(file));
                    }
                    else if (file.isDirectory())
                    {
                        addingJavaFilesFromDirectory(file);
                    }
                }
            }
        }
    }

    public FileIterator createFileParser() 
    {
        return new FileParser(files);
    }

    public boolean getExtension(String filePath)
    {
        String filename = filePath;
        if (filename.endsWith(".java")) 
        {
            //System.out.println(filename + " is a .java file");
            return true;
        } else 
        {
            //System.out.println(filename + " is not a .java file");
            return false;
        }
    }

    
}
