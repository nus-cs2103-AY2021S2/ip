package surrealchat.command;

import java.util.NoSuchElementException;

import surrealchat.task.TaskManagement;

/**
 * Command object for deleting all tasks objects, essentially emptying task list.
 */
public class ScronchCommand extends Command {
    /**
     * Creates new ScronchCommand object.
     */
    public ScronchCommand() {
        super("scronch");
    }

    private String printOutput(int size) {
        String outputString = "Scronching all tasks...\n";
        outputString += String.format("Total number of tasks: %d\n", size);
        return outputString;
    }

    /**
     * Executes scronch command to delete all tasks.
     * @param taskManagement TaskManagement object that handles Task objects relevant to command.
     * @return String output upon successful deletion of all tasks.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            taskManagement.deleteAllTasks();
            return printOutput(taskManagement.getNumberOfTasks());
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of scronch command.
     * @return String describing the scronch command.
     */
    public static String displayHelp() {
        return "Deletes all tasks from the list.\n";
    }
}
