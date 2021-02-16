package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;

public class ByeCommand extends Command {
    /**
     * Initialises the ByeCommand.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("Bye! I shall go rest now. PAge me when you need me!", CommandResultType.BYE);
    }

    public boolean isExit() {
        return true;
    }
}
