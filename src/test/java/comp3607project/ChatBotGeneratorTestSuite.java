package comp3607project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ChatBotGeneratorTestSuite extends TestCase {

    public ChatBotGeneratorTestSuite() {}

    @Test
    public void testCreateChatBotGenerator() {
        assertNotNull(new ChatBotGenerator());
        assertTrue((new ChatBotGenerator()) instanceof ChatBotGenerator);
    }
    

    @Test
    public void testGenerateChatBotLLM() {        
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(0));
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(6));
    }


    @Test
    public void testGenerateChatBotLLMCase1() {
        assertEquals("LLaMa", ChatBotGenerator.generateChatBotLLM(1));
    }


    @Test
    public void testGenerateChatBotLLMCase2() {
        assertEquals("Mistral7B", ChatBotGenerator.generateChatBotLLM(2));
    }


    @Test
    public void testGenerateChatBotLLMCase3() {
        assertEquals("Bard", ChatBotGenerator.generateChatBotLLM(3));
    }


    @Test
    public void testGenerateChatBotLLMCase4() {
        assertEquals("Claude", ChatBotGenerator.generateChatBotLLM(4));
    }


    @Test
    public void testGenerateChatBotLLMCase5() {
        assertEquals("Solar", ChatBotGenerator.generateChatBotLLM(5));
    }
}
