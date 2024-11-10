/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class JudgeSystem {
    

    public JudgeSystem() 
    {

    }

    public void evaluateSubmission (String filePath)
    {
        System.out.println ("Evaluating the submission of this file: " + filePath);
        //getExtension(filePath);
        //Code to do testing here
    }

    

    public void generateResults ()
    {
        System.out.println ("Generating the results PDF...");
        //Code to generate pdf here
    }

    public void unzipFiles(String filePath)
    {
        System.out.println("Unzipping the files from this file: " + filePath);
        //Code to unzip files here

        File dir = new File("extracted_files");
        if (!dir.exists()) 
        {
            dir.mkdir();
            //System.out.println("Created directory: " + dir.getAbsolutePath());
        }
        else{
            // System.out.println ("Directopry not created");
        }

        try (ZipInputStream zipInput = new ZipInputStream(Files.newInputStream(Path.of(filePath)))) {
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) 
            {
                File file = new File(dir, entry.getName());
                if (entry.isDirectory()) 
                {
                    file.mkdirs(); 
                    // System.out.println("Created directory: " + file.getAbsolutePath());
                    // System.out.println("Extracted file: " + file.getAbsolutePath());
                    
                } else 
                {
                    if (getExtension(file.getName()))
                    {
                        new File(file.getParent()).mkdirs();
                        Files.copy(zipInput, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    
                }
                zipInput.closeEntry();
            }
            System.out.println("Unzipping completed.");
            
            processExtractedFiles(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processExtractedFiles (File dir)
    {
        FileManager fileManager = new FileManager(dir);
        FileIterator iterator = fileManager.createFileParser();

        // while  (iterator.hasNext())
        // {
        //     FileType file = iterator.next();
        //     evaluateSubmission(file.getAbsolutePath());
        // }
    }


    public boolean getExtension(String filePath)
    {
        String filename = filePath;
        if (filename.endsWith(".java")) 
        {
            System.out.println(filename + " is a .java file");
            return true;
        } else 
        {
            System.out.println(filename + " is not a .java file");
            return false;
        }
    }
    
}
 
