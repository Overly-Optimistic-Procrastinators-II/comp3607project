/*
 * STUDENT ID:      816036508
 * STUDENT NAME:    Jonathan Mohammed
 */

public class ChatBotGenerator
{
    // Constructor - Default no argument
    public ChatBotGenerator() {
        
    }
    
    
    // Methods
    public static String generateChatBotLLM(int LLMCodeNumber) {
        switch(LLMCodeNumber) {
            case 1  : return "LLaMa";
            case 2  : return "Mistral7B";
            case 3  : return "Bard";
            case 4  : return "Claude";
            case 5  : return "Solar";
            default : return "ChatGPT-3.5";
        }
    }
}