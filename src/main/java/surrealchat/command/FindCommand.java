package surrealchat.command;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

/**
 * Command object for finding Tasks based on a keyword.
 */
public class FindCommand extends Command {
    protected final String keyword;

    /**
     * Creates new FindCommand object.
     * @param keyword The keyword to search. Case-insensitive.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    private String compileSearchResults(List<Task> searchResults) {
        String outputString = "";
        if (searchResults.isEmpty()) {
            throw new NoSuchElementException("My search returned nothing. Not stonks!\n");
        }
        outputString += "Here are my search results:\n";
        for (int i = 1; i <= searchResults.size(); i++) {
            outputString += String.format("%d. %s\n", i, searchResults.get(i - 1));
        }
        outputString += "Hmmst've... Stonks\n";
        return outputString;
    }

    /**
     * Executes find command to locate a Task based on keyword.
     * @param taskManagement TaskManagement object where Tasks are stored.
     * @return String of list of Tasks with keyword if any are found.
     */
    public String execute(TaskManagement taskManagement) {
        if (keyword.isEmpty()) {
            return "No keyword given! Not stonks!\n";
        }
        List<Task> searchResults = new ArrayList<Task>();
        List<Task> allTasks = taskManagement.getTaskList();
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(task);
            }
        }
        try {
            return this.compileSearchResults(searchResults);
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of find command.
     * @return String describing the find command.
     */
    public static String displayHelp() {
        String outputString = "Given a keyword, finds tasks that contain that keyword.\n";
        outputString += "Format of arguments: find [keyword]\n";
        return outputString;
    }
}
