package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;
// import org.junit.BeforeClass;

public class ChatBotSimulationTestSuite extends TestCase {
    
    public ChatBotSimulationTestSuite() {}

    @Test
    public void testCreateChatBotSimulation() {
        assertNotNull(new ChatBotSimulation());
        assertTrue((new ChatBotSimulation()) instanceof ChatBotSimulation);
    }


}
