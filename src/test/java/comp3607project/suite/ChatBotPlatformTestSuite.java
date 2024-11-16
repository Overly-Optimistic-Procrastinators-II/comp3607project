// package comp3607project.suite;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;

// import comp3607project.tool.ClassHolder;

// import java.util.ArrayList;
// // import org.junit.BeforeClass;

// public class ChatBotPlatformTestSuite {

//     private final ChatBotPlatform testPlatform = new ChatBotPlatform();
    
//     public ChatBotPlatformTestSuite() {}

//     @Test
//     public void testChatBotPlatformConstructor() {
//         // 2 marks
//         assertNotNull(new ChatBotPlatform());
//         assertTrue((new ChatBotPlatform()) instanceof ChatBotPlatform);
//         assertEquals(new ArrayList<ChatBot>(), testPlatform.getBots());
//     }


//     @Test
//     public void testAddChatBot() {
//         // 5 marks
//         Boolean result = testPlatform.addChatBot(0);
//         assertNotNull(result,"Method must return a non null value");
//         assertTrue(result == true || result == false,"Method returned non boolean value");
//         assertTrue(testPlatform.getChatBotList().contains("ChatBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
//     }


//     @Test
//     public void testGetChatBotList() {
//         // 8 marks
//         String response = testPlatform.getChatBotList();
//         assertNotNull(response,"Method must return a non null value");
//         assertTrue(response instanceof String,"Method returned non String value");
//         assertTrue(response.contains("ChartBot Name: ChatGPT-3.5"),"Incorrect Bot Created");
//     }


//     @Test
//     public void testInteractWithBot(){
//         // 5 marks
//         String response = testPlatform.interactWithBot(0, "Hello World");
//         assertNotNull(response,"Method must return a non null value");
//         assertTrue(response.contains("Incorrect Bot Number")||response.contains("Daily Limit Reached. Wait 24 hours to resume chatbot usage")||(response.contains("(Message#") && response.contains(")") && response.contains("Response from") && response.contains(">>")), "Method must adhere to the output format provided");
//     }

// }
