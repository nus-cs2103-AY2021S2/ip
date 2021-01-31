package surrealchat.command;

import java.util.NoSuchElementException;

import surrealchat.task.TaskManagement;

/**
 * Command object for listing out all tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates new ListCommand object.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes list command to list out all Tasks.
     * @param taskManagement TaskManagement object that stores all Task objects.
     * @return String of list of all Tasks.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            String outputString = "I print the tasks:\n";
            outputString += taskManagement.listOutTasks();
            outputString += "Hmmst've... Stonks\n";
            return outputString;
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of list command.
     * @return String describing the list command.
     */
    public static String displayHelp() {
        String outputString = "Displays full list of tasks.\n";
        outputString += "Format of arguments: list\n";
        return outputString;
    }
}
