package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
     * @param storage  The storage handler.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return taskList.addTask(this.type, this.fullCommand);
    }
}
