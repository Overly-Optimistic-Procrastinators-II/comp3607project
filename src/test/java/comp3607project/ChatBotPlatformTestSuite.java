package comp3607project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
// import org.junit.BeforeClass;

public class ChatBotPlatformTestSuite {

    private final ChatBotPlatform testPlatform = new ChatBotPlatform();
    
    public ChatBotPlatformTestSuite() {}

    @Test
    public void testCreateChatBotPlatform() {
        assertNotNull(new ChatBotPlatform());
        assertTrue((new ChatBotPlatform()) instanceof ChatBotPlatform);
        assertEquals(new ArrayList<ChatBot>(), testPlatform.getBots());//i dont know what this line is testing for.
    }

    @Test
    public void testAddChatBot() {
        Boolean result = testPlatform.addChatBot(0);
        assertNotNull(result,"Method must return a non null value");
        assertTrue(result == true || result == false,"Method returned non boolean value");
        assertTrue(testPlatform.getChatBotList().contains("ChatBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
    }

    @Test
    public void testInteractWithBot(){
        String response = testPlatform.interactWithBot(0, "Hello World");
        assertNotNull(response,"Method must return a non null value");
        assertTrue(response.contains("Incorrect Bot Number")||response.contains("Daily Limit Reached. Wait 24 hours to resume chatbot usage")||(response.contains("(Message#") && response.contains(")") && response.contains("Response from") && response.contains(">>")), "Method must adhere to the output format provided");
    }

}
