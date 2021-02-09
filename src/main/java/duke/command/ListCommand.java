package duke.command;

import duke.Storage;
import duke.task.TaskList;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Handles listing of tasks in TaskList
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     * @param command List command
     */
    public ListCommand(String command) {
        super.command = command;
        super.description = "";
        super.date = "";
    }

    private String getTaskListContents(TaskList tasks) {
        StringBuilder contents = new StringBuilder("Here are the tasks in your list:");
        AtomicInteger counter = new AtomicInteger(0);

        tasks.asList().forEach((task) -> {
            if (task != null) {
                contents.append(String.format("\n\t%d.%s", counter.intValue(), task.toString()));
            }
            counter.getAndIncrement();
        });

        return contents.toString();
    }

    @Override
    protected void updateOutput(Task task, TaskList tasks) {
        output = getTaskListContents(tasks);
    }

    /**
     * Outputs list of tasks to terminal
     *
     * @param tasks TaskList
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        updateOutput(null, tasks);
    }

}
