package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

/**
 * Represents a command telling duke to mark a task as complete
 */
public class DoneCommand implements Command {
    private int taskNum;

    /**
     * Constructor
     * 
     * @param taskNum Task number of the task to be marked as complete
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.taskNum > tasks.size()) {
            throw new DukeException("No such task exists!");
        }
        tasks.done(taskNum);

        ui.printMessage("Nice! I've marked this task as done:\n" + tasks.get(taskNum).toString());
    }
}
