package comp3607project.suite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import comp3607project.tool.ClassHolder;

class ChatBotSimulationTestSuite {
    
    public ChatBotSimulationTestSuite() {}

    private Class<?> ChatBotSimulation;
    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    protected String output;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() throws Exception{
        // new ChatBotSimulation();
        System.setOut(new PrintStream(outputStreamCaptor));
        invokeMain();
        output = outputStreamCaptor.toString();
    }
    

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown(){
        System.setOut(standardOut);
        outputStreamCaptor.reset();
    }


    @Test
    void testChatBotSimulation() {
        // 1 mark
        assertTrue(output.contains("Hello World"));
        assertEquals("Hello World!", output.lines().findFirst().get().trim());
    }


    @Test
    void testCreateChatBotPlatform() {
        // 1 mark
        assertTrue(output.contains("Your ChatBots"));
        assertTrue(output.indexOf("Hello World!") < output.indexOf("Your ChatBots"));
    }


    @Test
    void testAddChatBotToPlatform() {
        //2 marks

        //This is supposed to return true since the program mark scheme did specify that you should add all the chatbots at least once
        //TODO: ammend this test or the test subject
        assertTrue(output.contains("ChatGPT-3.5") && output.contains("LLaMa") && output.contains("Mistral7B") && output.contains("Bard") && output.contains("Claude") && output.contains("Solar"));
    }


    @Test
    void testChatBotPlatformGetChatBotList() {
        // 2 marks
        //Cannot figure out how to get the other mark out of this one but you can tell what I was doing with the output cap and what not
        assertTrue(output.contains("Bot Number"));
    }


    @Test
    void testChatBotPlatfomInteractWithBot() {
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
    void testChatBotPlatformGetChatBotListUpdated() {
        // 2 marks
        assertTrue(output.contains("Your ChatBots"), "There should be a list of chatbots");
        assertTrue(output.lastIndexOf("Your ChatBots") != output.indexOf("Your ChatBots"), "There should be a second list of chatbots");
        assertTrue(output.indexOf("Your ChatBots")<output.lastIndexOf("Your ChatBots"));
    }

    private void invokeMain() throws Exception {
        ChatBotSimulation.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
    }
}