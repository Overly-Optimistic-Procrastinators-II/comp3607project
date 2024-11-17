package comp3607project.suite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import comp3607project.tool.DynamicClassLoader;

public class ChatBotSimulationTestSuite {
    private Class<?> ChatBotSimulation;

    public ChatBotSimulationTestSuite() {}
    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    protected String output;

    @Before
    void setUp() {
        try {
            ChatBotSimulation = DynamicClassLoader.getClass("ChatBotSimulation");
            System.setOut(new PrintStream(outputStreamCaptor));
            invokeMain();
        } catch (ClassNotFoundException e) {
            ChatBotSimulation = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        output = outputStreamCaptor.toString();
    }
    

    @After
    void tearDown(){
        System.setOut(standardOut);
        outputStreamCaptor.reset();
    }


    @Test
    @TestMetaData(
        description = "Test LimitReached Case False", 
        marks = "1"
    )
    void testChatBotSimulation() {
        assertTrue(output.contains("Hello World"));
        assertEquals("Hello World!", output.lines().findFirst().get().trim());
    }


    @Test
    @TestMetaData(
        description = "Test LimitReached Case False", 
        marks = "1"
    )
    void testCreateChatBotPlatform() {
        assertTrue(output.contains("Your ChatBots"));
        assertTrue(output.indexOf("Hello World!") < output.indexOf("Your ChatBots"));
    }


    @Test
    @TestMetaData(
        description = "Test LimitReached Case False", 
        marks = "2"
    )
    void testAddChatBotToPlatform() {
        assertTrue(output.contains("ChatGPT-3.5") && output.contains("LLaMa") && output.contains("Mistral7B") && output.contains("Bard") && output.contains("Claude") && output.contains("Solar"));
    }


    @Test
    @TestMetaData(
        description = "Test LimitReached Case False", 
        marks = "2"
    )
    void testChatBotPlatformGetChatBotList() {
        assertTrue(output.contains("Bot Number"));
    }


    @Test
    @TestMetaData(
        description = "Test InteractWithBot", 
        marks = "4"
    )
    void testChatBotPlatfomInteractWithBot() {
        long firstInteractions = output.lines()
                            .filter(line->line.contains("Response from"))
                            .count();
        long secondInteractions = output.lines()
                                    .filter(line ->line.contains("Incorrect Bot Number") || line.contains("Daily Limit Reached. Wait 24 hours to resume chatbot usage"))
                                    .count();
        assertTrue(firstInteractions <=10, "There should be at most 10 interactions with a response message");
        assertTrue(secondInteractions >=5, "There should be at least 5 interactions with an Incorrect Bot message or a Daily limit message");
    }


    @Test
    @TestMetaData(
        description = "Test GetChatBotList After Simulation", 
        marks = "2"
    )
    void testChatBotPlatformGetChatBotListUpdated() {
        assertTrue(output.contains("Your ChatBots"), "There should be a list of chatbots");
        assertTrue(output.lastIndexOf("Your ChatBots") != output.indexOf("Your ChatBots"), "There should be a second list of chatbots");
        assertTrue(output.indexOf("Your ChatBots")<output.lastIndexOf("Your ChatBots"));
    }

    private void invokeMain() throws Exception {
        ChatBotSimulation.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
    }
}