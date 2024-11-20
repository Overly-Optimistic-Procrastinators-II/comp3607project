/**
 * Author: Jonathan Mohammed
 */

package comp3607project.suite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import comp3607project.tool.DynamicClassLoader;

public class ChatBotGeneratorTestSuite {
    private Class<?> ChatBotGenerator;

    @BeforeClass
    public void setup() {
        try {
            ChatBotGenerator = DynamicClassLoader.getClass("ChatBotGenerator");
        } catch (Exception e) {
            ChatBotGenerator = null;
        }
    }

    @AfterClass
    public void tearDown() {
        ChatBotGenerator = null;
    }

    
    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns ChatGPT-3.5 Lower Bound", 
        marks = "1"
    )
    public void testGenerateChatBotLLMLowerBound() {
        assertEquals("ChatGPT-3.5", invokeGenerateChatBotLLM(0));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns ChatGPT-3.5 Upper Bound", 
        marks = "1"
    )
    public void testGenerateChatBotLLMUpperBound() {
        assertEquals("ChatGPT-3.5", invokeGenerateChatBotLLM(6));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns LLama", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsLLaMa() {
        assertEquals("LLaMa", invokeGenerateChatBotLLM(1));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Mistral7B", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsMistral7B() {
        assertEquals("Mistral7B", invokeGenerateChatBotLLM(2));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Bard", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsBard() {
        assertEquals("Bard", invokeGenerateChatBotLLM(3));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Claude", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsClaude() {
        assertEquals("Claude", invokeGenerateChatBotLLM(4));
    }


    @Test
    @TestMetaData(
        description = "Test GenerateChatBotLLM returns Solar", 
        marks = "1"
    )
    public void testGenerateChatBotLLMReturnsSolar() {
        assertEquals("Solar", invokeGenerateChatBotLLM(5));
    }


    private String invokeGenerateChatBotLLM(int code) {
        try {
            return (String) ChatBotGenerator.getMethod("generateChatBotLLM", int.class).invoke(null, code);
        } catch (Exception e) {
            return null;
        }
    }
}
