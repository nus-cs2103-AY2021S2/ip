package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;

public class UnknownCommand extends Command {
    /**
     * Initialises the UnknownCommand.
     */
    public UnknownCommand(String command) {
        super(command);
    }

    /**
     * Executes the command..
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("I can't help you with this command yet. Sorry!", CommandResultType.ERROR);
    }

    public boolean isExit() {
        return false;
    }
}
