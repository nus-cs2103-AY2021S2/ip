package duke.command;

import duke.exception.DukeStorageException;
import duke.task.Task;
import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing a Done Command.
 */
public class DoneCommand extends Command {
    private final int completedTaskIdx;

    /**
     * Constructor for DoneCommand.
     *
     * @param completedTaskIdx The index of the completed task.
     */
    public DoneCommand(int completedTaskIdx) {
        this.completedTaskIdx = completedTaskIdx;
    }

    /**
     * Executes the done command.
     * Marks the task as done.
     * Updates the task storage.
     * Prompts the user that the task has been marked done.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Updates tasks.txt of the completed task.
     * @return Duke's response after setting task as done.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        try {
            Task completedTask = tasks.getTask(completedTaskIdx);
            completedTask.setDone();
            storage.storeData(tasks);
            return messageFormatter.formatDoneMsg(completedTask);
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }
}
