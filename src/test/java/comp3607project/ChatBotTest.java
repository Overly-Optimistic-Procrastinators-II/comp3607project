package comp3607project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
public class ChatBotTest extends TestCase{
    

    private static ChatBot tester;
    private static ChatBot customTester;



    @Before
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
        assertNotNull("Method must return a non-null variable", tester.getChatBotName());
        assertTrue("Method should return a String type variable", tester.getChatBotName() instanceof String);
        assertEquals("The default name for the ChatBot should be 'ChatGPT-3.5'", "ChatGPT-3.5", tester.getChatBotName());
    }

    @Test
    public void testGetChatBotNameCustom(){
        //Tests the default name the constructor assigns to the chatbot
        assertNotEquals("The name for the ChatBot should not be the default 'ChatGPT-3.5'", "ChatGPT-3.5", customTester.getChatBotName());
    }

    @Test
    public void testGetChatBotName(){
        //Tests the return value of the accessor is a string and is not null
        assertNotNull("Method must return a non-null variable", tester.getChatBotName());
        assertTrue("Method should return a String type variable", tester.getChatBotName() instanceof String);
    }

    @Test
    public void testGetNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        tester.prompt("Hello World!");
        assertNotNull("Method must return a non-null variable", tester.getNumResponsesGenerated());
        assertTrue("Method should return an Integer type variable", tester.getNumResponsesGenerated() >= 0);
        assertEquals("Number of generated responses being returned is not correct",1,tester.getNumResponsesGenerated());
    }

    @Test
    public void testMessageLimit() {
        //Tests the return value of the accessor is an integer, is not null and is equal to 10 (Message limit)
        assertNotNull(tester.getMessageLimit());
        assertTrue("Method should return an Integer type variable", tester.getMessageLimit() >= 0);
        assertEquals("Message limit should be 10", tester.getMessageLimit(), 10);
    }
    
    @Test
    public void testGetTotalNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        customTester.prompt("Hello World!");
        tester.prompt("Hello World!");
        assertNotNull("Method must return a non-null variable", ChatBot.getTotalNumResponsesGenerated());
        assertTrue("Method should return an Integer type variable", ChatBot.getTotalNumResponsesGenerated() >= 0);
        assertEquals("Method is returning incorrect total number of responses generated", 2, ChatBot.getTotalNumResponsesGenerated());
    }
    
    @Test
    public void testLimitNotReached(){
        assertNotNull("Method must return a non-null variable", ChatBot.limitReached());
        assertTrue("Method should return a Boolean type variable", ChatBot.limitReached() == true || ChatBot.limitReached() == false);
        assertFalse("Method is incorrectly returning true (Stating that limit has been reached)", ChatBot.limitReached());
    }

    @Test
    public void testLimitReached(){
        for(int i = 0; i < 5; i++){
            customTester.prompt("Hello World");
            tester.prompt("Hello World");
        }
        assertNotNull("Method must return a non-null variable", ChatBot.limitReached());
        assertTrue("Method should return a Boolean type variable", ChatBot.limitReached() == true || ChatBot.limitReached() == false);
        assertTrue("Method is incorrectly returning false (Stating that limit has not been reached)", ChatBot.limitReached());
    }

    @Test
    public void testPromptResponse(){
        String response = tester.prompt("Hello World");
        assertNotNull("Method must return a non-null variable", response);
        assertTrue("Method should return a String type variable", response instanceof String);
        assertTrue("Method must adhere to the output format provided", response.contains("(Message#") && response.contains(")") && response.contains("Response from") && response.contains(">>"));
    }

    @Test
    public void testPromptResponseLimitReached() {
        for (int i = 0; i < 10; i++)
            tester.prompt("Hello World");
        String response = tester.prompt("Are you there?");
        
        assertEquals("Response should indicate limit reached after exceeding message limit","Daily Limit Reached. Wait 24 hours to resume chatbot usage", response);
    }

}
