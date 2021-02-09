package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
        String contents = "Here are the tasks in your list:";

        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task is null";
            contents += String.format("\n\t%d.%s", i, task.toString());
        }

        return contents;
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
