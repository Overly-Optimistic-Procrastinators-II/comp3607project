/*
 * Receiver
 */

package comp3607project;

public class JudgeSystem {
    

    public JudgeSystem() 
    {

    }

    public void evaluateSubmission (String filePath)
    {
        System.out.println ("Evaluating the submission of this file: " + filePath);
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
    }
    
}
