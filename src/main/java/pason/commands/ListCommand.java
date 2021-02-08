package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;

public class ListCommand extends Command {
    /**
     * Initialises the AddCommand.
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null.";
        if (tasks.getTasks().size() == 0) {
            return new CommandResult("There are no tasks in your list. Time to add some!",
                    CommandResultType.CHAT_PASON);
        }
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            output += (i + 1) + ". " + tasks.getTasks().get(i) + "\n";
        }
        return new CommandResult(output, CommandResultType.CHAT_PASON);
    }

    public boolean isExit() {
        return false;
    }
}
