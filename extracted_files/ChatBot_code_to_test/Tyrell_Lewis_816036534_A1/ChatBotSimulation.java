//Student ID: 816036534

import java.util.Random;

public class ChatBotSimulation
{
    public static void main (String [] args)
    {
        System.out.println ("Hello World!");
        
        ChatBotPlatform chatBotPlatform;
        chatBotPlatform = new ChatBotPlatform();
        
        int y =0;
        
        for (y = 0; y<7; y++)
        {
            
            if (chatBotPlatform.addChatBot(y) == false)
            {
                System.out.println ("Could not add more chatbots!");
                break;
            }
        }
        
        System.out.println(chatBotPlatform.getChatBotList());
    
        int x = 0;
        
        Random rand = new Random ();
        
        for (x = 0; x < 15; x++)
        {   
            String requestMessage = "Begin conversation with the ChatBots";
            
            String response = chatBotPlatform.interactWithBot(rand.nextInt(10), requestMessage);
            System.out.println (response);
        }
        
        System.out.println(chatBotPlatform.getChatBotList());
    }
}