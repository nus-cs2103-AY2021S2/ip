package duke.commands;

import duke.DukeException;
import duke.ParserUtils;
import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.models.Todo;
import duke.ui.Ui;

/**
 * Handles the Todo command of the user to create new todos in the list.
 * Format of command: "todo [todo_name]".
 */
public class TodoCommand implements Command {
    private final String taskName;

    protected TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curTask = new Todo(taskName);
        tasks.addTask(curTask);
        ui.printTaskListStatus(tasks, curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a new instance of Todo Command
     *
     * @param argString string with argument
     * @return instance of Todo Command
     * @throws DukeException
     */
    public static TodoCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "The description of a todo cannot be empty.");

        assert (cmdArgs[0].equals("todo"));

        String taskName = cmdArgs[1];
        return new TodoCommand(taskName);
    }
}
