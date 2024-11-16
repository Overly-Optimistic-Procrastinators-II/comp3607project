package comp3607project;

public class ZipFileHandler {
    

    private boolean isJavaFile(String filePath) {
        return ((filePath.endsWith(".java") ? true : false));
    }
}
