package comp3607project.tool;

public class DynamicClassLoader implements BaseClassLoader {
    

    public Class<?> getClass(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }
}
