package comp3607project.tool;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class DynamicJavaCompiler {
    public static void compile(String directory) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if(compiler == null) {
            System.out.println("No java compiler available.\n");
            System.out.println("Application requires a Java Development Kit (JDK) to be installed.\n");
            return;
        }

        if(compiler.run(null, null, null, directory) != 0) {
            System.out.println("Compilation failed");
        }
    }
}
