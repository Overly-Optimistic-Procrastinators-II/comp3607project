package comp3607project.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;

public class DynamicClassLoader extends ClassLoader implements BaseClassLoader {
    private String classPath;

    public DynamicClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public Class<?> getClass(String name) throws ClassNotFoundException {
        String filePath = classPath + File.separator + name.replace('.', File.separatorChar) + ".class";
        File classFile = new File(filePath);

        if(!classFile.exists()) {
            throw new ClassNotFoundException("Class not found at " + filePath);
        }

        byte[] data;
        try (InputStream inputStream = new FileInputStream(classFile)) {
            data = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new ClassNotFoundException("Error reading class file at " + filePath);
        }

        return defineClass(name, data, 0, data.length);
    }
}
