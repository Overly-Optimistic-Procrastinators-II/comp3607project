package comp3607project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ChatBotTestSuite {
    private static ChatBot tester;
    private static ChatBot customTester;

    @BeforeEach
    public void initialize() {
        // Initialize the Chatbot objects
        try {
            tester = new ChatBot();
            customTester = new ChatBot(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ChatBot objects", e);
        }
    }

    @Test
    public void testCreateChatBot() {
        assertNotNull(new ChatBot());
        assertTrue((new ChatBot()) instanceof ChatBot);
    }

    @Test
    public void testGetChatBotNameDefault(){
        //Tests the default name the constructor assigns to the chatbot
        assertNotNull(tester.getChatBotName(), "Method must return a non-null variable");
        assertTrue(tester.getChatBotName() instanceof String, "Method should return a String type variable");
        assertEquals("ChatGPT-3.5", tester.getChatBotName(), "The default name for the ChatBot should be 'ChatGPT-3.5'");
    }

    @Test
    public void testGetChatBotNameCustom(){
        //Tests the default name the constructor assigns to the chatbot
        assertNotEquals("ChatGPT-3.5", customTester.getChatBotName(), "The name for the ChatBot should not be the default 'ChatGPT-3.5'");
    }

    @Test
    public void testGetChatBotName(){
        //Tests the return value of the accessor is a string and is not null
        assertNotNull(tester.getChatBotName(), "Method must return a non-null variable");
        assertTrue(tester.getChatBotName() instanceof String, "Method should return a String type variable");
    }

    @Test
    public void testGetNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        tester.prompt("Hello World!");
        assertNotNull(tester.getNumResponsesGenerated(), "Method must return a non-null variable");
        assertTrue(tester.getNumResponsesGenerated() >= 0, "Method should return an Integer type variable");
        assertEquals(1, tester.getNumResponsesGenerated(), "Number of generated responses being returned is not correct");
    }

    @Test
    public void testMessageLimit() {
        //Tests the return value of the accessor is an integer, is not null and is equal to 10 (Message limit)
        assertNotNull(tester.getMessageLimit(), "Method must return a non-null variable");
        assertTrue(tester.getMessageLimit() >= 0, "Method should return an Integer type variable");
        assertEquals(tester.getMessageLimit(), 10, "Message limit should be 10");
    }
    
    @Test
    public void testGetTotalNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        customTester.prompt("Hello World!");
        tester.prompt("Hello World!");
        assertNotNull(ChatBot.getTotalNumResponsesGenerated(), "Method must return a non-null variable");
        assertTrue(ChatBot.getTotalNumResponsesGenerated() >= 0, "Method should return an Integer type variable");
        assertEquals(2, ChatBot.getTotalNumResponsesGenerated(), "Method is returning incorrect total number of responses generated");
    }
    
    @Test
    public void testLimitNotReached(){
        assertNotNull(ChatBot.limitReached(), "Method must return a non-null variable");
        assertTrue(ChatBot.limitReached() == true || ChatBot.limitReached() == false, "Method should return a Boolean type variable");
        assertFalse(ChatBot.limitReached(), "Method is incorrectly returning true (Stating that limit has been reached)");
    }

    @Test
    public void testLimitReached(){
        for(int i = 0; i < 5; i++){
            customTester.prompt("Hello World");
            tester.prompt("Hello World");
        }
        assertNotNull(ChatBot.limitReached(), "Method must return a non-null variable");
        assertTrue(ChatBot.limitReached() == true || ChatBot.limitReached() == false, "Method should return a Boolean type variable");
        assertTrue(ChatBot.limitReached(), "Method is incorrectly returning false (Stating that limit has not been reached)");
    }

    @Test
    public void testPromptResponse(){
        String response = tester.prompt("Hello World");
        assertNotNull(response, "Method must return a non-null variable");
        assertTrue(response instanceof String, "Method should return a String type variable");
        assertTrue(response.contains("(Message#") && response.contains(")") && response.contains("Response from") && response.contains(">>"), "Method must adhere to the output format provided");
    }

    @Test
    public void testPromptResponseLimitReached() {
        for (int i = 0; i < 10; i++)
            tester.prompt("Hello World");
        String response = tester.prompt("Are you there?");
        
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage", response, "Response should indicate limit reached after exceeding message limit");
    }
}
