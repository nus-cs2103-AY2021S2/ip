package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;

public class DoneCommand extends Command {
    protected int index;
    /**
     * Initialises the DoneCommand.
     */
    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Executes the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null.";
        try {
            return new CommandResult(tasks.doneTask(this.index), CommandResultType.CHAT_PASON);
        } catch (Exception e) {
            return new CommandResult(e.getMessage(), CommandResultType.ERROR);
        }
    }

    public boolean isExit() {
        return false;
    }
}
