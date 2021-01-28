package surrealchat.command;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

import java.util.List;
import java.util.NoSuchElementException;

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
        String outputString = "I print the tasks:\n";
        outputString += taskManagement.listOutTasks();
        outputString += "Hmmst've... Stonks\n";
        return outputString;
    }
}
