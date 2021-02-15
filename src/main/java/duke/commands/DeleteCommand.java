package duke.commands;

import duke.DukeException;
import duke.ParserUtils;
import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.ui.Ui;

/**
 * Handles the Delete command of the user to delete a certain task in the list.
 * Format of command: "delete [task_index]".
 */
public class DeleteCommand implements Command {
    private final int index;

    protected DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert (index <= tasks.size());

        Task curTask = tasks.getTask(index - 1);
        tasks.removeTask(index - 1);
        ui.printIndentOutput("Nice! I've removed this task:");
        ui.printIndentOutput("   " + curTask);
        ui.printIndentOutput("Now you have " + tasks.size() + " task(s) in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }


    /**
     * Creates a new instance of Delete Command
     *
     * @param argString string with argument
     * @return instance of Delete Command
     * @throws DukeException
     */
    public static DeleteCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "I'm sorry, but delete needs the index of a Task.");

        assert (cmdArgs[0].equals("delete"));

        int index = ParserUtils.parseInt(cmdArgs[1], "The index of the task needs to be an integer.");
        return new DeleteCommand(index);
    }
}
