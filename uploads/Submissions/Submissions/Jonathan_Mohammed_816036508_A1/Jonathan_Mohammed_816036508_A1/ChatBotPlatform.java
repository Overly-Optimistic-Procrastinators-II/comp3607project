/*
 * STUDENT ID:      816036508
 * STUDENT NAME:    Jonathan Mohammed
 */

import java.util.ArrayList;

public class ChatBotPlatform
{
    // Attributes
    private ArrayList<ChatBot> bots;
    
    
    // Constructor - Default no argument
    public ChatBotPlatform() {
        this.bots = new ArrayList<ChatBot>();
    }
    
    
    // Accessors
    public ArrayList<ChatBot> getBots() {
        return this.bots;
    }
    
    
    // Methods
    public boolean addChatBot(int LLMcode) {
        if (!ChatBot.limitReached()) {
            ChatBot newBot = new ChatBot(LLMcode);
            bots.add(newBot);
            return true;
        }
        
        return false;
    }
    
    public String getChatBotList() {
        String chatBotList = ("-------------------------" + "\n" + "Your ChatBots" + "\n");
        
        for (ChatBot bot : bots) {
            chatBotList += ("Bot Number: " + bots.indexOf(bot) + " " + bot.toString() + "\n");
        }
        
        chatBotList += ("Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated() + "\n" +
                        "Total Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n" +
                        "-------------------------");
        
        return chatBotList;
    }
    
    public String interactWithBot(int botNumber, String message) {
        ArrayList<ChatBot> botCollection = getBots();
        if (botNumber >= botCollection.size()) {
            return ("Incorrect Bot Number (" + botNumber + ") Selected. Try again");
        }
        
        ChatBot bot = botCollection.get(botNumber);
        
        return bot.prompt(message);
    }
}