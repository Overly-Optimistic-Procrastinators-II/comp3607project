package comp3607project.tool;

public class ClassHolder {
    private static Class<?> ChatBotGenerator;
    private static Class<?> ChatBot;
    private static Class<?> ChatBotPlatform;
    private static Class<?> ChatBotSimulation;

    public ClassHolder(String classPath) {
        initialize();
        DynamicClassLoader loader = new DynamicClassLoader(classPath);
        
        try {
            setChatBotGenerator(loader);
            setChatBot(loader);
            setChatBotPlatform(loader);
            setChatBotPlatform(loader);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void initialize() {
        ChatBotGenerator = null;
        ChatBot = null;
        ChatBotPlatform = null;
        ChatBotSimulation = null;
    }

    public static Class<?> getChatBotGenerator() {
        return ChatBotGenerator;
    }

    public static Class<?> getChatBot() {
        return ChatBot;
    }

    public static Class<?> getChatBotPlatform() {
        return ChatBotPlatform;
    }

    public static Class<?> getChatBotSimulation() {
        return ChatBotSimulation;
    }

    public static void setChatBotGenerator(DynamicClassLoader loader) {
        try {
            ChatBotGenerator = loader.getClass("ChatBotGenerator");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
    }

    public static void setChatBot(DynamicClassLoader loader) {
        try {
            ChatBot = loader.getClass("ChatBot");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
    }

    public static void setChatBotPlatform(DynamicClassLoader loader) {
        try {
            ChatBotPlatform = loader.getClass("ChatBotPlatform");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
    }

    public static void setChatBotSimulation(DynamicClassLoader loader) {
        try {
            ChatBotSimulation = loader.getClass("ChatBotSimulation");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
    }
}
