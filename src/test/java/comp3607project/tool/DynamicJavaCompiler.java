package comp3607project.tool;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

public class DynamicJavaCompiler {
    public static void compile(String directory) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        validateCompiler(compiler);

        File[] files = getFiles(directory);
        String[] config = configureCompiler(files, directory);
        
        if(compiler.run(null, null, null, config) != 0) {
            System.out.println("Compilation failed");
        }
    }

    private static void validateCompiler(JavaCompiler compiler) {
        if(compiler == null) {
            System.out.println("No java compiler available.\n");
            System.out.println("Application requires a Java Development Kit (JDK) to be installed.\n");
            return;
        }
    }

    private static String[] configureCompiler(File[] files, String directory) {
        String[] config = new String[files.length + 2];
        config[0] = "-classpath";
        config[1] = directory;

        for (int i = 0; i < files.length; i++) {
            config[i + 2] = files[i].getAbsolutePath();
        }

        return config;
    }

    private static File[] getFiles(String directory) {
        File srcDirectory = new File(directory);
        File[] files = srcDirectory.listFiles((d, name) -> name.endsWith(".java"));

        if (files == null || files.length == 0) {
            System.out.println("No java files found in this directory");
            return null;
        }

        return files;
    }
}
