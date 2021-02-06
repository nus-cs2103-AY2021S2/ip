package surrealchat.command;

import surrealchat.exception.SurrealException;
import surrealchat.task.TaskManagement;

/**
 * Command object for sorting tasks.
 */
public class SortCommand extends Command {
    protected final String sortBy;

    /**
     * Creates SortCommand object.
     * @param sortBy Keyword to obtain correct comparator.
     */
    public SortCommand(String sortBy) {
        super("sort");
        this.sortBy = sortBy;
    }

    /**
     * Executes sort command to sort tasks.
     * @param taskManagement TaskManagement object that handles Task objects relevant to command.
     * @return Message upon successful sorting of task.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            taskManagement.sort(sortBy);
            return "I have sorted the tasks. Stonks!\n";
        } catch (SurrealException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of sort command.
     * @return String describing sort command.
     */
    public static String displayHelp() {
        String outputString = "Sorts tasks in list.\n";
        outputString += "Format of arguments: sort [criteria]\n";
        return outputString;
    }
}
