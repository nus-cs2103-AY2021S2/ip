package popo.commands;

import static popo.utils.Messages.MESSAGE_FOUND_TASKS;
import static popo.utils.Messages.MESSAGE_INDEX_TASK_FORMAT;
import static popo.utils.Messages.MESSAGE_NO_MATCHES;

import popo.tasks.Task;

/**
 * Finds tasks that contains specific keywords or phrases.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Find tasks that contains specific keywords or phrases\n"
            + "Usage: find <keyword_or_phrase>"
            + "Example: find book";

    private final String searchWord;

    /**
     * Creates a {@code FindCommand} object with the given search word or phrase.
     *
     * @param searchWord Keyword or phrase to be used for filtering.
     */
    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public CommandResult execute() {
        String tasksWithSearchWord = findTasks();
        String trimmedTasksString = tasksWithSearchWord.trim();

        if (trimmedTasksString.length() == 0) {
            return new CommandResult(false, MESSAGE_NO_MATCHES);
        }
        return new CommandResult(false,
                MESSAGE_FOUND_TASKS + "\n",
                trimmedTasksString);
    }

    private String findTasks() {
        assert taskList != null;
        StringBuilder tasksWithSearchWord = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i - 1);
            if (task.getName().contains(searchWord)) {
                tasksWithSearchWord.append(String.format(MESSAGE_INDEX_TASK_FORMAT, i, task.toString()));
                tasksWithSearchWord.append("\n");
            }
        }
        return tasksWithSearchWord.toString();
    }
}
