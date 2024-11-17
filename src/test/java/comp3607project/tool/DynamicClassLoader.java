package comp3607project.tool;

import java.net.URLClassLoader;

public class DynamicClassLoader {
    private static URLClassLoader classLoader;

    public static Class<?> getClass(String name) throws ClassNotFoundException {
        try {
            return Class.forName(name, true, classLoader);
        } catch (Exception e) {
            return null;
        }
    }
}
