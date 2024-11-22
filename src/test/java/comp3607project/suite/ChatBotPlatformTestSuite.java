/**
 * Author: Varun Maharaj
 * Last Edited By: Jonathan Mohammed
 */

package comp3607project.suite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import comp3607project.tool.DynamicClassLoader;

public class ChatBotPlatformTestSuite {
    private Class<?> ChatBotPlatform;/** Stores the class of the ChatBotPlatform */
    
    public ChatBotPlatformTestSuite() {}

    @BeforeClass
    public void setup() {
        try {
            ChatBotPlatform = DynamicClassLoader.getClass("ChatBotPlatform");
        } catch (Exception e) {
            ChatBotPlatform = null;
        }
    }


    @AfterClass
    public void tearDown() {
        ChatBotPlatform = null;
    }

    // @Test
    // @TestMetaData(
    //     description = "Test ChatBotPlatform Constructor",
    //     marks = "2"
    // )
    // public void testChatBotPlatformConstructor() {
    //     assertNotNull("ChatGPT-3.5", invoke);
    //     assertTrue((new ChatBotPlatform()) instanceof ChatBotPlatform);
    //     assertEquals(new ArrayList<ChatBot>(), testPlatform.getBots());
    // }


    @Test
    @TestMetaData(
        description = "Test AddChatBot", 
        marks = "5"
    )
    public void testAddChatBot() throws Exception {
        Boolean result = invokeChatBotPlatformAddChatBot(0);
        assertNotNull(result,"Method must return a non null value");
        assertTrue(result == true || result == false,"Method returned non boolean value");
        // assertTrue(testPlatform.getChatBotList().contains("ChatBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
    }


    @Test
    @TestMetaData(
        description = "Test GetChatBotList", 
        marks = "8"
    )
    public void testGetChatBotList() throws Exception {
        String response = invokeChatBotPlatformGetChatBotList();
        assertNotNull(response,"Method must return a non null value");
        assertTrue(response instanceof String,"Method returned non String value");
        assertTrue(response.contains("ChartBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
    }


    @Test
    @TestMetaData(
        description = "Test InteractWithBot", 
        marks = "5"
    )
    public void testInteractWithBot() throws Exception {
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
