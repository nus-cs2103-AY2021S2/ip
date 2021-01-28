package surrealchat.command;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Command object for finding Tasks based on a keyword.
 */
public class FindCommand extends Command{
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
            throw new NoSuchElementException("My search returned nothing. Not stonks!");
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
            throw new IllegalArgumentException("No keyword given! Not stonks!");
        }
        List<Task> searchResults = new ArrayList<Task>();
        List<Task> allTasks = taskManagement.getTaskList();
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(task);
            }
        }
        return this.compileSearchResults(searchResults);
    }
}
