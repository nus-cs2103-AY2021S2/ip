package duke.command;

import java.io.IOException;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.task.TaskList;

/**
 * Represents a command telling duke to mark a task as complete
 */
public class DoneCommand implements Command {
    private int taskNum;

    /**
     * Constructor
     * @param taskNum Task number of the task to be marked as complete
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (this.taskNum > tasks.size()) {
            throw new DukeException("No such task exists!");
        }
        tasks.done(taskNum);

        try {
            storage.store(tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot save tasks. Save file not found");
        }

        String doneResponse = "Nice! I've marked this task as done:\n  " + tasks.get(taskNum).toString();
        return doneResponse;
    }
}
