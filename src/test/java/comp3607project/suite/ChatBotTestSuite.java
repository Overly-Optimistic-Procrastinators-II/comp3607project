package comp3607project.suite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.lang.reflect.Constructor;

import org.junit.Before;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import comp3607project.JudgeSystem;
import comp3607project.tool.ClassHolder;

public class ChatBotTestSuite {
    // private static Object tester;
    // private static Object customTester;
    private Class<?> ChatBot;

    @Before
    public void setup() {
        @SuppressWarnings("unused")
        ClassHolder holder = new ClassHolder(JudgeSystem.getUploadPath());
        ChatBot = ClassHolder.getChatBot();
    }

    // @BeforeEach
    // public void initialize() {
    //     try {
    //         Constructor<?> testConstructor = ChatBot.getConstructor();
    //         Constructor<?> customConstructor = ChatBot.getConstructor(int.class);
    //         tester = testConstructor.newInstance();
    //         customTester = customConstructor.newInstance(1);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         throw new RuntimeException("Failed to initialize ChatBot objects", e);
    //     }
    // }


    @Test
    @TestMetaData(
        description = "Test ChatBot Constructor", 
        marks = "3"
    )
    public void testChatBotConstructor() throws Exception {
        assertEquals("ChatGPT-3.5", invokeGetChatBotName());
    }


    @Test
    @TestMetaData(
        description = "Test ChatBot Overloaded Constructor", 
        marks = "3"
    )
    public void testChatBotOverloadedConstructor() throws Exception {
        assertEquals("LLaMa", invokeGetChatBotName());
    }


    @Test
    @TestMetaData(
        description = "Test GetChatBotName", 
        marks = "2"
    )
    public void testGetChatBotName() throws Exception {
        assertNotNull(invokeGetChatBotName(), "Method must return a non-null variable");
        assertTrue(invokeGetChatBotName() instanceof String, "Method should return a String type variable");
    }


    @Test
    @TestMetaData(
        description = "TestMessageLimit", 
        marks = "3"
    )
    public void testMessageLimit() throws Exception {
        assertNotNull(invokeGetMessageLimit(), "Method must return a non-null variable");
        assertTrue(invokeGetMessageLimit() >= 0, "Method should return an Integer type variable");
        assertEquals(invokeGetMessageLimit(), 10, "Message limit should be 10");
    }


    @Test
    @TestMetaData(
        description = "Test GetNumResponsesGenerated", 
        marks = "2"
    )
    public void testGetNumResponsesGenerated() throws Exception {
        invokePrompt("Hello World!");
        assertNotNull(invokeGetNumResponsesGenerated(), "Method must return a non-null variable");
        assertTrue(invokeGetNumResponsesGenerated() >= 0, "Method should return an Integer type variable");
        assertEquals(1, invokeGetNumResponsesGenerated(), "Number of generated responses being returned is not correct");
    }


    @Test
    @TestMetaData(
        description = "Test GetTotalNumResponsesGenerated", 
        marks = "4"
    )
    public void testGetTotalNumResponsesGenerated() throws Exception {
        invokePrompt("Hello World!");
        invokePrompt("Hello World!");
        assertNotNull(invokeGetTotalNumResponsesGenerated(), "Method must return a non-null variable");
        assertTrue(invokeGetTotalNumResponsesGenerated() >= 0, "Method should return an Integer type variable");
        assertEquals(2, invokeGetTotalNumResponsesGenerated(), "Method is returning incorrect total number of responses generated");
    }


    @Test
    @TestMetaData(
        description = "Test GetTotalNumMessagesRemaining", 
        marks = "3"
    )
    public void testGetTotalNumMessagesRemaining() throws Exception {
        assertNotNull(invokeGetTotalNumMessagesRemaining(), "Method must return a non-null variable");
        assertTrue(invokeGetTotalNumMessagesRemaining() >= 0, "Method should return an Integer type variable");
        assertEquals(8, invokeGetTotalNumMessagesRemaining(), "Number of generated responses being returned is not correct");
    }

    
    @Test
    @TestMetaData(
        description = "Test LimitReached Case False", 
        marks = "1"
    )
    public void testLimitReachedFalse() throws Exception {
        assertNotNull(invokeLimitReached(), "Method must return a non-null variable");
        assertTrue(invokeLimitReached() == true || invokeLimitReached() == false, "Method should return a Boolean type variable");
        assertFalse(invokeLimitReached(), "Method is incorrectly returning true (Stating that limit has been reached)");
    }


    @Test
    @TestMetaData(
        description = "Test LimitReached Case True", 
        marks = "2"
    )
    public void testLimitReachedTrue() throws Exception {
        for(int i = 0; i < 10; i++){
            invokePrompt("Hello World!");
        }
        assertNotNull(invokeLimitReached(), "Method must return a non-null variable");
        assertTrue(invokeLimitReached() == true || invokeLimitReached() == false, "Method should return a Boolean type variable");
        assertTrue(invokeLimitReached(), "Method is incorrectly returning false (Stating that limit has not been reached)");
    }


    @Test
    @TestMetaData(
        description = "Test GenerateResponse", 
        marks = "5"
    )
    public void testGenerateResponse() throws Exception {
        String response = invokePrompt("Hello World");
        assertNotNull(response, "Method must return a non-null variable");
        assertTrue(response instanceof String, "Method should return a String type variable");
        assertTrue(response.contains("(Message#") && response.contains(")") && response.contains("Response from") && response.contains(">>"), "Method must adhere to the output format provided");
    }


    @Test
    @TestMetaData(
        description = "Test PromptResponse", 
        marks = "4"
    )
    public void testPromptResponse() throws Exception {
        for (int i = 0; i < 10; i++)
            invokePrompt("Hello World");
        String response = invokePrompt("Are you there?");
        
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage", response, "Response should indicate limit reached after exceeding message limit");
    }


    @Test
    @TestMetaData(
        description = "Test ToString", 
        marks = "4"
    )
    public void testToString() throws Exception {
        assertTrue(invokeToString() instanceof String, "Method should return a String type variable");
    }


    // private void invokeChatBot() throws Exception {
    //     return (Object) ChatBot.get
    // }

    private String invokeGetChatBotName() throws Exception {
        return (String) ChatBot.getMethod("getChatBotName").invoke(null);
    }

    private int invokeGetMessageLimit() throws Exception {
        return (int) ChatBot.getMethod("getMessageLimit").invoke(null);
    }

    private int invokeGetNumResponsesGenerated() throws Exception {
        return (int) ChatBot.getMethod("getNumResponsesGenerated").invoke(null);
    }

    private int invokeGetTotalNumResponsesGenerated() throws Exception {
        return (int) ChatBot.getMethod("getTotalNumResponsesGenerated").invoke(null);
    }

    private int invokeGetTotalNumMessagesRemaining() throws Exception {
        return (int) ChatBot.getMethod("getTotalNumMessagesRemaining").invoke(null);
    }

    private boolean invokeLimitReached() throws Exception {
        return (boolean) ChatBot.getMethod("limitReached").invoke(null);
    }

    private String invokePrompt(String message) throws Exception {
        return (String) ChatBot.getMethod("prompt", String.class).invoke(null, message);
    }

    private String invokeToString() throws Exception {
        return (String) ChatBot.getMethod("toString", String.class).invoke(null);
    }
}
