package comp3607project.suite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import comp3607project.JudgeSystem;
import comp3607project.tool.ClassHolder;

import java.util.ArrayList;
// import org.junit.BeforeClass;

public class ChatBotPlatformTestSuite {

    // private final ChatBotPlatform testPlatform = new ChatBotPlatform();
    private Class<?> ChatBotPlatform;
    
    public ChatBotPlatformTestSuite() {}

    @Before
    public void setup() {
        @SuppressWarnings("unused")
        ClassHolder holder = new ClassHolder(JudgeSystem.getUploadPath());
        ChatBotPlatform = ClassHolder.getChatBotPlatform();
    }
    
    // @Test
    // public void testChatBotPlatformConstructor() {
    //     // 2 marks
    //     assertNotNull("ChatGPT-3.5", invoke);
    //     assertTrue((new ChatBotPlatform()) instanceof ChatBotPlatform);
    //     assertEquals(new ArrayList<ChatBot>(), testPlatform.getBots());
    // }

    @Test
    public void testAddChatBot() throws Exception {
        // 5 marks
        Boolean result = invokeChatBotPlatformAddChatBot(0);
        assertNotNull(result,"Method must return a non null value");
        assertTrue(result == true || result == false,"Method returned non boolean value");
        // assertTrue(testPlatform.getChatBotList().contains("ChatBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
    }

    @Test
    public void testGetChatBotList() throws Exception{
        // 8 marks

        String response = invokeChatBotPlatformGetChatBotList();
        assertNotNull(response,"Method must return a non null value");
        assertTrue(response instanceof String,"Method returned non String value");
        assertTrue(response.contains("ChartBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
    }


    @Test
    public void testInteractWithBot() throws Exception{
        // 5 marks
        String response = invokeChatBotPlatformInteractWithBot(0, "Hello World");
        assertNotNull(response,"Method must return a non null value");
        assertTrue(response.contains("Incorrect Bot Number")||response.contains("Daily Limit Reached. Wait 24 hours to resume chatbot usage")||(response.contains("(Message#") && response.contains(")") && response.contains("Response from") && response.contains(">>")), "Method must adhere to the output format provided");
    }

    private Boolean invokeChatBotPlatformAddChatBot(int LLMcode) throws Exception {
        return (Boolean) ChatBotPlatform.getMethod("addChatBot", int.class).invoke(null, LLMcode);
    }

    private String invokeChatBotPlatformGetChatBotList() throws Exception {
        return (String) ChatBotPlatform.getMethod("getChatBotList").invoke(null);
    }

    private String invokeChatBotPlatformInteractWithBot(int LLMcode, String message) throws Exception {
        return (String) ChatBotPlatform.getMethod("interactWithBot", int.class).invoke(null, LLMcode, message);
    }

}
