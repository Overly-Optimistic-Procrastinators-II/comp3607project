/**
 * Author: Jonathan Mohammed
 */

package comp3607project.tool;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

public class DynamicJavaCompiler {
    private String directory;

    public DynamicJavaCompiler(String directory) {
        this.directory = directory;
    }

    public boolean compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        
        if(compiler == null) {
            return false;
        }

        File[] files = getFiles(directory);
        String[] config = configureCompiler(files, directory);

        return (compiler.run(null, null, null, config) == 0);
    }

    private String[] configureCompiler(File[] files, String directory) {
        String[] config = new String[files.length + 2];
        config[0] = "-classpath";
        config[1] = directory;

        for (int i = 0; i < files.length; i++) {
            config[i + 2] = files[i].getAbsolutePath();
        }

        return config;
    }

    private File[] getFiles(String directory) {
        File srcDirectory = new File(directory);
        File[] files = srcDirectory.listFiles((d, name) -> name.endsWith(".java"));

        if (files == null || files.length == 0) {
            System.out.println("No java files found in this directory");
            return null;
        }

        return files;
    }
}
