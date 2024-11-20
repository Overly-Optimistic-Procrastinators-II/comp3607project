/**
 * Author: Tyrell Lewis
 * 
 * Command Design Pattern
 * Command
 * Declares an interface for all commands providing a simple execute method
 * which asks the receiver (JudgeSystem) to carry out an operation
 */

package comp3607project.command;

public interface Command {
    public void execute();
}
