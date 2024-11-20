/**
 * Author: Tyrell Lewis
 * 
 * Command Design Pattern
 * Invoker
 * This class holds a command and gets the Command interface to execute a request by 
 * calling the execute method
 */

package comp3607project.command;

public class Invoker {
    private Command command; 

    public Invoker() {}

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        }
        else {
            System.out.println("No command set.");
        }
    }
}
