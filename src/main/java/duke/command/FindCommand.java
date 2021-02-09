package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        var filteredTasks = tasks.asList().stream().filter(task -> {
            if (task != null) {
                boolean isMatch = task.getDescription().toLowerCase().contains(description.toLowerCase());
                if (isMatch) {
                    return true;
                }
                return false;
            }
            return false;
        }).collect(Collectors.toList());

        StringBuilder contents = new StringBuilder("Here are the tasks in your list:");
        AtomicInteger counter = new AtomicInteger(1);

        filteredTasks.forEach((task) -> {
            contents.append(String.format("\n\t%d.%s", counter.intValue(), task.toString()));
            counter.getAndIncrement();
        });
      
        return contents.toString();
    }

    @Override
    protected void updateOutput(Task task, TaskList tasks) {
        output = getMatchedTaskListContents(tasks);
    }

    /**
     * Outputs list of keyword-matched tasks
     *
     * @param tasks TaskList
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        updateOutput(null, tasks);
    }

}
