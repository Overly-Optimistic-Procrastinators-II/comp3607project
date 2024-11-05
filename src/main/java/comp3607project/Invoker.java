package comp3607project;

public class Invoker 
{
    private CommandInterface command; 

    public void setCommand(CommandInterface command)
    {
        this.command = command;
    }

    public void pressButton()
    {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set.");
        }
    }
}