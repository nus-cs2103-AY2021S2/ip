package duke.command;

import duke.Storage;
import duke.task.TaskList;

import java.util.concurrent.atomic.AtomicInteger;

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
        StringBuilder contents = new StringBuilder("Here are the tasks in your list:");
        AtomicInteger counter = new AtomicInteger(1);

        tasks.asList().forEach((task) -> {
            if (task != null) {
                boolean isMatch = task.getDescription().toLowerCase().contains(description.toLowerCase());
                if (isMatch) {
                    contents.append(String.format("\n\t%d.%s", counter.intValue(), task.toString()));
                    counter.getAndIncrement();
                }
            }
        });

        return contents.toString();
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
