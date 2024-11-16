package comp3607project.suite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import comp3607project.ChatBotSimulation;

public class ChatBotSimulationTestSuite {
    
    public ChatBotSimulationTestSuite() {}
    private ChatBotSimulation sim;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    protected String output;

    @BeforeEach
    void setUp(){
        sim = new ChatBotSimulation();
        System.setOut(new PrintStream(outputStreamCaptor));
        ChatBotSimulation.main(new String[]{});
        output = outputStreamCaptor.toString();
    }

    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
        outputStreamCaptor.reset();
    }

    @Test
    public void testChatBotSimulation() {
        // 1 mark
        assertTrue(output.contains("Hello World"));
        assertEquals("Hello World!", output.lines().findFirst().get().trim());
    }


    @Test
    public void testCreateChatBotPlatform() {
        // 1 mark
        assertTrue(output.contains("Your ChatBots"));
        assertTrue(output.indexOf("Hello World!") < output.indexOf("Your ChatBots"));
    }


    @Test
    public void testAddChatBotToPlatform() {
        // 2 marks
        // long errorCount = output.lines().filter(line -> line.contains("Error")).count();
        // assertEquals(0, errorCount, "There should be no errors when adding bots");

        //This is supposed to return true since the program mark scheme did specify that you should add all the chatbots at least once
        //TODO: ammend this test or the test subject
        assertTrue(output.contains("ChatGPT-3.5") && output.contains("LLaMa") && output.contains("Mistral7B") && output.contains("Bard") && output.contains("Claude") && output.contains("Solar"));

    }


    @Test
    public void testChatBotPlatformGetChatBotList() {
        // 2 marks
        assertTrue(output.contains("Bot Number"));

        //TODO: cannot figure out how to get the other mark out of this one but you can tell what I was doing with the output cap and what not
        //NewJeans Ditto might be the greatest song on this planet.
    }


    @Test
    public void testChatBotPlatfomInteractWithBot() {
        // 4 marks
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
    public void testChatBotPlatformGetChatBotListUpdated() {
        // 2 marks


        int firstListIndex = output.indexOf("Your ChatBots");
        int secondListIndex = output.lastIndexOf("Your ChatBots");

        assertTrue(output.indexOf("Your ChatBots") != -1, "There should be a list of chatbots");
        assertTrue(output.lastIndexOf("Your ChatBots") != output.indexOf("Your ChatBots"), "There should be a second list of chatbots");
        assertTrue(output.indexOf("Your ChatBots")<output.lastIndexOf("Your ChatBots"));
    }
}
