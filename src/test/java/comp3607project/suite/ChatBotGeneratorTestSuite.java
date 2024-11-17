package comp3607project.suite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import comp3607project.tool.ClassHolder;
import comp3607project.JudgeSystem;

public class ChatBotGeneratorTestSuite {
    private Class<?> ChatBotGenerator;

    public ChatBotGeneratorTestSuite() {}


    @Before
    public void setup() {
        @SuppressWarnings("unused")
        ClassHolder holder = new ClassHolder(JudgeSystem.getUploadPath());
        ChatBotGenerator = ClassHolder.getChatBotGenerator();
    }

    
    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns ChatGPT-3.5 Lower Bound", 
        marks = "1"
    )
    public void testGenerateChatBotLowerBound() throws Exception {
        assertEquals("ChatGPT-3.5", invokeGenerateChatBotLLM(0));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns ChatGPT-3.5 Upper Bound", 
        marks = "1"
    )
    public void testGenerateChatBotLLMUpperBound() throws Exception {
        assertEquals("ChatGPT-3.5", invokeGenerateChatBotLLM(6));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns LLama", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsLLaMa() throws Exception {
        assertEquals("LLaMa", invokeGenerateChatBotLLM(1));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Mistral7B", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsMistral7B() throws Exception {
        assertEquals("Mistral7B", invokeGenerateChatBotLLM(2));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Bard", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsBard() throws Exception {
        assertEquals("Bard", invokeGenerateChatBotLLM(3));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Claude", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsClaude() throws Exception {
        assertEquals("Claude", invokeGenerateChatBotLLM(4));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Solar", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsSolar() throws Exception {
        assertEquals("Solar", invokeGenerateChatBotLLM(5));
    }


    private String invokeGenerateChatBotLLM(int code) throws Exception {
        return (String) ChatBotGenerator.getMethod("generateChatBotLLM", int.class).invoke(null, code);
    }
}
