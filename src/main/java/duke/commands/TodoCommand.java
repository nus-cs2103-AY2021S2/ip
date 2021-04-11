package duke.commands;

import duke.DukeException;
import duke.ParserUtils;
import duke.TaskList;
import duke.models.Task;
import duke.models.Todo;

/**
 * Handles the Todo command of the user to create new todos in the list.
 * Format of command: "todo [todo_name]".
 */
public class TodoCommand extends AddCommand {
    protected TodoCommand(String taskName) {
        super(taskName);
    }

    @Override
    public Task getTask() {
        return new Todo(getTaskName());
    }

    @Override
    public boolean isTaskValid(Task task, TaskList tasks) {
        return true;
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
