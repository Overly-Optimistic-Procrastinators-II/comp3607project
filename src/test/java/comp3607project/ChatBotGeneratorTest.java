package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;

public class ChatBotGeneratorTest extends TestCase {

    public ChatBotGeneratorTest() {}

    @Test
    public void testCreateChatBotGenerator() {
        assertNotNull(new ChatBotGenerator());
        assertTrue((new ChatBotGenerator()) instanceof ChatBotGenerator);
    }
    

    @Test
    public void testGenerateChatBotLLM() {
        assertEquals("LLaMa", ChatBotGenerator.generateChatBotLLM(1));
        assertEquals("Mistral7B", ChatBotGenerator.generateChatBotLLM(2));
        assertEquals("Bard", ChatBotGenerator.generateChatBotLLM(3));
        assertEquals("Claude", ChatBotGenerator.generateChatBotLLM(4));
        assertEquals("Solar", ChatBotGenerator.generateChatBotLLM(5));
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(0));
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(6));
    }
}
