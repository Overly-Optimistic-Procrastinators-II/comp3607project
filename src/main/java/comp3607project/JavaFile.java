package comp3607project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JavaFile implements FileType {
     public static void main(String[] args){
        String zipFilePath = "/src/main/resources/bonkywonky.zip";
        String destDir = "/src/main/resources";
        unzip(zipFilePath, destDir);
     }

     public static void unzip(String zipFilePath, String destDir){
        File dir = new File(destDir);
        if(!dir.exists()) dir.mkdirs(); //Create new directory if one doesnt already exist
        FileInputStream fileInput;
        byte[] buffer = new byte[1024];
        try{
            fileInput = new FileInputStream(zipFilePath);
            ZipInputStream zipInput = new ZipInputStream(fileInput);
            ZipEntry entry = zipInput.getNextEntry();
            while(entry != null){
                String fileName = entry.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fileOutput = new FileOutputStream(newFile);
                int len;
                while((len = zipInput.read(buffer)) > 0)
                    fileOutput.write(buffer,0, len);
                fileOutput.close();
                zipInput.closeEntry();
                entry = zipInput.getNextEntry();
            }
            zipInput.closeEntry();
            zipInput.close();
            fileInput.close();
        } catch(IOException e){
            e.printStackTrace();
        }

     }

    
}
