package duke.commands;

import duke.DukeException;
import duke.ParserUtils;
import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.ui.Ui;

/**
 * Handles the Done command of the user to mark tasks as done.
 * Format of command: "done [task_index]".
 */
public class DoneCommand implements Command {
    private final int index;

    protected DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.size()) {
            throw new DukeException("The index of the task needs to be present in the list.");
        }

        assert (tasks.size() > 0);

        Task curTask = tasks.getTask(index - 1);
        curTask.markAsDone();
        ui.printIndentOutput("Nice! I've marked this task as done:");
        ui.printIndentOutput("   " + curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a new instance of Done Command
     *
     * @param argString string with argument
     * @return instance of Done Command
     * @throws DukeException
     */
    public static DoneCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "I'm sorry, but done needs the index of a Task.");

        assert (cmdArgs[0].equals("done"));

        int index = ParserUtils.parseInt(cmdArgs[1], "The index of the task needs to be an integer.");
        return new DoneCommand(index);
    }
}
