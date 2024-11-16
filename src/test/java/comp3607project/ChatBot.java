package comp3607project;

public class ChatBot
{
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    @SuppressWarnings("unused")
    public ChatBot ()
    {
        ChatBotGenerator chatBotGenerator = new ChatBotGenerator();
        
        chatBotName = "ChatGPT-3.5";
    }
    
    @SuppressWarnings("static-access")
    public ChatBot (int LLMCode)
    {
        ChatBotGenerator chatBotGenerator = new ChatBotGenerator();
        
        chatBotName = chatBotGenerator.generateChatBotLLM(LLMCode);
    }
    
    public String getChatBotName ()
    {
        return this.chatBotName;
    }
    
    public int getNumResponsesGenerated ()
    {
        return this.numResponsesGenerated;
    }
    
    @SuppressWarnings("static-access")
    public int getMessageLimit ()
    {
        return this.messageLimit;
    }
    
    public static int getTotalNumResponsesGenerated ()
    {
        return messageNumber;
    }
    
    public static int getTotalNumMessagesRemaining() 
    {
         return (messageLimit -  messageNumber);
    }
    
    public static boolean limitReached()
    {
        if (getTotalNumResponsesGenerated() >= messageLimit)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private String generateResponse()
    {
       
       String botResponse = "(Message# " + (getTotalNumResponsesGenerated()+1) + ")" + " Response from " + getChatBotName() + "\t >>generatedTextHere";
       
       numResponsesGenerated += 1;
       messageNumber += 1;
       
       return botResponse;
    }
    
    public String prompt (String requestMessage)
    {
        if (limitReached() == false)
        {
            return (generateResponse());    
        }
        else
        {
            return ("Daily Limit Reached. Wait 24 hours to resume chatbot usage");
        }
    }
    
    public String toString()
    {
        return ("ChatBot Name: " + getChatBotName() + "\t Number Messages Used: " + getNumResponsesGenerated());
    }
}