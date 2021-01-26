package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A AddTaskCommand class to execute add task.
 */
public class AddTaskCommand extends Command {
    protected String type;
    protected String fullCommand;

    /**
     * Constructs AddTaskCommand.
     *
     * @param type        The type of task.
     * @param fullCommand The command given.
     */
    public AddTaskCommand(String type, String fullCommand) {
        super();
        this.type = type;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add task depending on the type of task.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage handler.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(this.type, this.fullCommand);
    }

    /**
     * Program does not exit.
     *
     * @return False to continue the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
