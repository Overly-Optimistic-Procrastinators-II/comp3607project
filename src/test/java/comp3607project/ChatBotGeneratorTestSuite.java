package comp3607project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Annotation {
    public String key();
    public String value();
}

public class ChatBotGeneratorTestSuite {

    public ChatBotGeneratorTestSuite() {}    

    
    @Test
    @DisplayName("Test GenerateChatBotLLM returns ChatGPT-3.5 Lower Bound")
    @Tag("1")
    public void testGenerateChatBotLowerBound() {
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(0));
    }


    @Test
    @DisplayName("Test GenerateChatBotLLM returns ChatGPT-3.5 Upper Bound")
    @Tag("1")
    public void testGenerateChatBotLLMUpperBound() {
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(6));
    }


    @Test
    @DisplayName("Test GenerateChatBotLLM returns LLama")
    @Tag("1")
    public void testGenerateChatBotLLMReturnsLLaMa() {
        assertEquals("LLaMa", ChatBotGenerator.generateChatBotLLM(1));
    }


    @Test
    @DisplayName("Test GenerateChatBotLLM returns Mistral7B")
    @Tag("1")
    public void testGenerateChatBotLLMReturnsMistral7B() {
        assertEquals("Mistral7B", ChatBotGenerator.generateChatBotLLM(2));
    }


    @Test
    @DisplayName("Test GenerateChatBotLLM returns Bard")
    @Tag("1")
    public void testGenerateChatBotLLMReturnsBard() {
        assertEquals("Bard", ChatBotGenerator.generateChatBotLLM(3));
    }


    @Test
    @DisplayName("Test GenerateChatBotLLM returns Claude")
    @Tag("1")
    public void testGenerateChatBotLLMReturnsClaude() {
        assertEquals("Claude", ChatBotGenerator.generateChatBotLLM(4));
    }


    @Test
    @DisplayName("Test GenerateChatBotLLM returns Solar")
    @Tag("1")
    public void testGenerateChatBotLLMReturnsSolar() {
        assertEquals("Solar", ChatBotGenerator.generateChatBotLLM(5));
    }
}
