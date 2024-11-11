/*
 * Receiver
 */

package comp3607project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;


public class JudgeSystem {
    
    private String extractedFilePath;

    public JudgeSystem() 
    {

    }

    public void evaluateSubmission (String filePath)
    {
        System.out.println ("Evaluating the submission of this file: " + filePath);
        //getExtension(filePath);
        //Code to do testing here
    }

    public void generateResults() throws DocumentException, FileNotFoundException
    {   
        System.out.println ("Directory for pdf: " + getExtractedFilePath());
        // File extractedFilesDir = new File("extracted_files");
        // File[] directories = extractedFilesDir.listFiles(File::isDirectory);
        // File targetDir = new File("");
        // if (directories != null && directories.length > 0) 
        // {
        //    targetDir = directories[0];
        // }

        File targetDir = new File (getExtractedFilePath());
        if (!targetDir.exists() || !targetDir.isDirectory())
        {
            System.out.println ("No valid directory was found!");
            return;
        }

        
        //Code to generate pdf here
        Document doc = new Document();

        try{

            PdfWriter.getInstance(doc, new FileOutputStream(new File(targetDir, "results.pdf")));
 
            doc.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Chunk chunk = new Chunk("Hello World", font);

            doc.add(chunk);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File could not be created " + e.getMessage());
        }
        catch (DocumentException e)
        {
            System.out.println("Error ocurred with PDF document " + e.getMessage());
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            doc.close();
        }
        
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

        String zipFileName = new File(filePath).getName();
        String folderName = zipFileName.substring(0, zipFileName.lastIndexOf('.'));
        File subDir = new File(dir, folderName);

        if (!subDir.exists()) {
            subDir.mkdir();
            //System.out.println("Created subdirectory: " + subDir.getAbsolutePath());
        }

        try (ZipInputStream zipInput = new ZipInputStream(Files.newInputStream(Path.of(filePath)))) {
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) 
            {
                File file = new File(subDir, entry.getName());
                if (entry.isDirectory()) 
                {
                    file.mkdirs(); 
                    // System.out.println("Created directory: " + file.getAbsolutePath());
                    System.out.println("Extracted file: " + file.getAbsolutePath());
                    
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

            extractedFilePath = subDir.getAbsolutePath();
            System.out.println ("Extracted file path: " + getExtractedFilePath());
            processExtractedFiles(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processExtractedFiles (File dir)
    {
        // FileManager fileManager = new FileManager(dir);
        // FileIterator iterator = fileManager.createFileParser();

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
            //System.out.println(filename + " is a .java file");
            return true;
        } else 
        {
            //System.out.println(filename + " is not a .java file");
            return false;
        }
    }

    public String getExtractedFilePath()
    {
        return extractedFilePath;
    }
    
}
 
