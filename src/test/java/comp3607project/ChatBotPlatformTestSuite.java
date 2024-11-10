package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
// import org.junit.BeforeClass;

public class ChatBotPlatformTestSuite extends TestCase {

    private final ChatBotPlatform testPlatform = new ChatBotPlatform();
    
    public ChatBotPlatformTestSuite() {}

    @Test
    public void testCreateChatBotPlatform() {
        assertNotNull(new ChatBotPlatform());
        assertTrue((new ChatBotPlatform()) instanceof ChatBotPlatform);
        assertEquals(new ArrayList<ChatBot>(), testPlatform.getBots());
    }



}
