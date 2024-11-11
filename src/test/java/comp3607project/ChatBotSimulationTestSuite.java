package comp3607project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
// import org.junit.BeforeClass;

public class ChatBotSimulationTestSuite extends TestCase {
    
    public ChatBotSimulationTestSuite() {}

    @Test
    public void testCreateChatBotSimulation() {
        assertNotNull(new ChatBotSimulation());
        assertTrue((new ChatBotSimulation()) instanceof ChatBotSimulation);
    }


}
