/*
 * STUDENT ID:      816036508
 * STUDENT NAME:    Jonathan Mohammed
 */

package comp3607project;

import java.util.Random;

public class ChatBotSimulation
{
    // Constructor - Default no argument
    public ChatBotSimulation() {
        
    }
    
    
    // Methods
    public static void main (String args[]) {
        System.out.println("Hello World!");
        
        ChatBotPlatform platform = new ChatBotPlatform();
        
        // Adding ChatBots to the platform
        int numBots = 7;
        for (int botCode = 0; botCode < numBots; botCode++) {
            if (!platform.addChatBot(botCode)) {
                System.out.println("Error: Could not add ChatBot");
            }
        }
        
        // ChatBot list and summary statistics
        System.out.println(platform.getChatBotList());
        
        // Interacting with ChatBots normally
        Random random = new Random();
        for (int x = 0; x < 10; x++) {
            System.out.println(platform.interactWithBot(random.nextInt(numBots), "Hello"));
        }
        
        // Interacting with ChatBot not in platform
        System.out.println(platform.interactWithBot(numBots, "Hello"));
        
        // Interacting with ChatBot when message limit reached
        for (int x = 0; x < 4; x++) {
            System.out.println(platform.interactWithBot(random.nextInt(numBots), "Hello"));
        }
        
        // ChatBot list and summary statistics
        System.out.println(platform.getChatBotList());
    }
}