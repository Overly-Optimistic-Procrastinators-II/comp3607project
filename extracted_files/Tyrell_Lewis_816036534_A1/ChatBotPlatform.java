//Student ID: 816036534

import java.util.ArrayList;

public class ChatBotPlatform
{
    private ArrayList<ChatBot> bots;
    
    public ChatBotPlatform()
    {
        bots = new ArrayList<>();
    }
    
    public ArrayList<ChatBot> getBots()
    {
        return this.bots;
    }
    
    public boolean addChatBot (int LLMCode)
    {
        if (ChatBot.limitReached() == true)
        {
            return false;
        }
        else
        {
            bots.add (new ChatBot (LLMCode));
            
            return true;
        }
    }
    
    public String getChatBotList ()
    {
        String message = "-------------------- \nYour ChatBots";
        
        for (int x =0; x< bots.size(); x++)
        {
            message = message + "\nBot Number: " + x + " ChatBot Name: " + getBots().get(x).toString();
        }
        
        message = message + "\nTotal Messages Used: " + ChatBot.getTotalNumResponsesGenerated() + "\nTotal Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n--------------------";
        
        return message;
    }
    
    public String interactWithBot (int botNumber, String message)
    {   
        if (botNumber >= 0 && botNumber < bots.size())
        {
            String response = getBots().get(botNumber).prompt(message);
            
            return (response);
        }
        else
        {
            if (ChatBot.limitReached() == true)
            {
                return ("Daily Limit Reached. Wait 24 hours to resume chatbot usage");
            }
            
            return ("Incorrect Bot Number (" + botNumber + ") Selected. Try again");
        }
    }
}