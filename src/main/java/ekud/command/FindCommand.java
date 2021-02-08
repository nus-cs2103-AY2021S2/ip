package ekud.command;

import java.util.Arrays;

import ekud.storage.Storage;
import ekud.task.TaskList;

/**
 * Command that goes through the entire list and displays all the tasks with descriptions containing a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Construct a command that uses a keyword as filter and display the matching tasks.
     *
     * @param keyword The keyword to match against the descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints all tasks with descriptions that contain the specified keyword.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     * @return String containing all the tasks that matches the keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        var matchingTasks = tasks.stream().filter(task -> task.getDescription().contains(keyword)).toArray();

        StringBuilder replyBuilder = new StringBuilder(matchingTasks.length == 0
                ? "Nothing found, try another keyword?"
                : "Here's what I found!");
        Arrays.stream(matchingTasks).forEach(task -> replyBuilder
                .append(System.lineSeparator())
                .append("  ").append(task.toString()));

        return replyBuilder.toString();
    }
}
