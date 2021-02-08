package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles finding matching keywords of a task name
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand
     * @param command Find command
     * @param keyword Keyword to match
     */
    public FindCommand(String command, String keyword) {
        super.command = command;
        super.description = keyword;
    }

    /**
     * Searches for keyword specified and filter out only tasks with description matches
     *
     * @param tasks TaskList
     * @return List of keyword-matched tasks to output
     */
    private String getMatchedTaskListContents(TaskList tasks) {
        String contents = "Here are the matching tasks in your list:";
        // Tracks index of previously matched task
        int prevIndex = 1;
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task is null";
            if (task.getDescription().toLowerCase().contains(description.toLowerCase())) {
                contents += String.format("\n\t%d.%s", prevIndex, task.toString());
                prevIndex++;
            }
        }

        return contents;
    }

    /**
     * Outputs list of keyword-matched tasks
     *
     * @param tasks TaskList
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        output = getMatchedTaskListContents(tasks);
    }

    /**
     * Determines if Exit is called by user
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
