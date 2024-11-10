//TODO Move the test cases and other test-related code to the src\main\java
package comp3607project;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestChatBot{
    /**
     * 
     * Rigorous Test :-)
     */

     private ChatBot tester;
     private ChatBot customTester;

    @BeforeClass
    public void setUp() throws Exception {
        // Initialize the Chatbot objects
        tester = new ChatBot();
        customTester = new ChatBot(1);
    }
    @Test
    public void testConstructor_ChatBot(){
        //Tests the default name the constructor assigns to the chatbot
        assertNotNull(customTester.getChatBotName());
        assertTrue(customTester.getChatBotName() instanceof String, "Method should return a String type variable");
        assertEquals(customTester.getChatBotName(), "ChatGPT-3.5");
    }

    @Test
    public void testConstructor_ChatBot_Overloaded(){
        //Tests the overloaded constructor that assigns a custom name to the chatbot
        assertNotNull(customTester.getChatBotName());
        assertTrue(customTester.getChatBotName() instanceof String, "Method should return a String type variable");
        assertNotEquals(customTester.getChatBotName(), "ChatGPT-3.5");
    }

    @Test
    public void testMethod_getChatBotName(){
        //Tests the return value of the accessor is a string and is not null
        assertNotNull(tester.getChatBotName());
        assertTrue(tester.getChatBotName() instanceof String, "Method should return a String type variable");
    }

    @Test
    public void testMethod_getNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        tester.prompt("Hello World!");
        tester.prompt("How are you World!");
        assertNotNull(tester.getNumResponsesGenerated());
        assertTrue(tester.getNumResponsesGenerated() instanceof Integer, "Method should return an Integer type variable");
        assertEquals(tester.getNumResponsesGenerated,2);
    }

    @Test
    public void testMethod_getTotalNumResponsesGenerated(){
        //Tests the return value of the accessor is an integer and is not null
        
        assertNotNull(tester.getTotalNumResponsesGenerated());
        assertTrue(tester.getTotalNumResponsesGenerated() instanceof Integer, "Method should return an Integer type variable");
        assertEquals(tester.getTotalNumResponsesGenerated,2);
    }

    @Test
    public void testMethod_limitReached(){
        assertNotNull(tester.limitReached());
        assertTrue(tester.limitReached() instanceof Boolean, "Method should return a Boolean type variable");
        assertFalse(tester.limitReached());
    }

    @Test
    public void {

    }
}
