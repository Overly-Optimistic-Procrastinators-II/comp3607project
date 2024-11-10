//TODO Move the test cases and other test-related code to the src\main\java
package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 * Unit test for simple App.
 */
public class ChatBotTest extends TestCase{
    /**
     * 
     * Rigorous Test :-)
     */

    private static ChatBot tester;
    private static ChatBot customTester;

    @BeforeClass
    public static void initialize() {
        // Initialize the Chatbot objects
        try {
            tester = new ChatBot();
            customTester = new ChatBot(1);
        } catch (Exception e) {
            // do something
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
        assertNotNull(customTester.getChatBotName());
        assertTrue("Method should return a String type variable", customTester.getChatBotName() instanceof String);
        assertEquals("The default name for the ChatBot should be 'ChatGPT-3.5'", "ChatGPT-3.5", tester.getChatBotName());
    }

    @Test
    public void testGetChatBotNameCustom(){
        //Tests the overloaded constructor that assigns a custom name to the chatbot
        assertNotNull(customTester.getChatBotName());
        assertTrue("Method should return a String type variable", customTester.getChatBotName() instanceof String);
        assertNotEquals(customTester.getChatBotName(), "ChatGPT-3.5");
    }

    @Test
    public void testGetChatBotName(){
        //Tests the return value of the accessor is a string and is not null
        assertNotNull(tester.getChatBotName());
        assertTrue("Method should return a String type variable", tester.getChatBotName() instanceof String);
    }

    @Test
    public void testInitialGetNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        assertNotNull(tester.getNumResponsesGenerated());
        assertTrue("Method should return an Integer type variable", tester.getNumResponsesGenerated() >= 0);
        assertEquals("The initial number of responses generated should be 0", tester.getNumResponsesGenerated(), 0);
    }

    @Test
    public void testGetNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        tester.prompt("Hello World!");
        assertNotNull(tester.getNumResponsesGenerated());
        assertTrue("Method should return an Integer type variable", tester.getNumResponsesGenerated() >= 0);
        assertEquals(tester.getNumResponsesGenerated(),1);
    }

    @Test
    public void testGetTotalNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        customTester.prompt("Hello World!");
        assertNotNull(ChatBot.getTotalNumResponsesGenerated());
        assertTrue("Method should return an Integer type variable", ChatBot.getTotalNumResponsesGenerated() >= 0);
        assertEquals(3, ChatBot.getTotalNumResponsesGenerated());
    }

    @Test
    public void testGetNumMessagesRemaining(){
        //Test the return value of the accessor is an integer, not null and is a correct value
        assertNotNull(ChatBot.getTotalNumResponsesGenerated());
        assertTrue("Method should return an Integer type variable", ChatBot.getTotalNumResponsesGenerated() >= 0);
        assertEquals(ChatBot.getTotalNumMessagesRemaining(), (10 - ChatBot.getTotalNumResponsesGenerated()));
    }

    @Test
    public void testLimitReached(){
        assertNotNull(ChatBot.limitReached());
        assertTrue("Method should return a Boolean type variable", ChatBot.limitReached() == true || ChatBot.limitReached() == false);
        assertFalse(ChatBot.limitReached());
    }

    // You cant test a private method btw
    // Either call the public method and verify the output or just dont test it if it does the same
    // this as another test

    //TODO : Figure out how to create the test for the method generateResponse() as it is a private method.
    // @Test
    // public void testMethod_generateResponse(){
    //     assertNotNull(tester.prompt("Hello World!"));
    //     assertTrue(tester.prompt("Hello World!") instanceof String, "Method should return a String type variable");
        
    // }

    @Test
    public void testPrompt(){
        //Test the return value of the method is a string and is not null
        String response = tester.prompt("Hello World!");
        assertNotNull(response, "Method must return a non-null variable");
        assertTrue("Method should return a String type variable", response instanceof String);
    }

    @Test
    public void testToString(){
        //Test the return value of the method is a string and is not null
        String response = tester.toString();
        assertNotNull(response, "Method must return a non-null variable");
        assertTrue("Method should return a String type variable", response instanceof String);
    }
    
}
