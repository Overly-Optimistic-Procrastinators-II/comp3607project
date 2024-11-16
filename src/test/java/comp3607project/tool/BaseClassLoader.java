package comp3607project.tool;

public interface BaseClassLoader {
    public Class<?> getClass(String name) throws ClassNotFoundException;
}
