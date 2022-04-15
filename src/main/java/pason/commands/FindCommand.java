package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;

public class FindCommand extends Command {
    private final String keyword;
    /**
     * Initialises the FindCommand.
     */
    public FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    /**
     * Exxecutes the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null.";
        if (tasks.getTasks().size() == 0) {
            return new CommandResult("There are no tasks in your list. Time to add some!",
                    CommandResultType.CHAT_PASON);
        }
        int matchingResults = 0;
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (tasks.getTasks().get(i).getDescription().toLowerCase().contains(this.keyword.toLowerCase())) {
                output += (i + 1) + ". " + tasks.getTasks().get(i) + "\n";
                matchingResults++;
            }
        }
        if (matchingResults == 0) {
            return new CommandResult("There are no matching tasks with that keyword.",
                    CommandResultType.CHAT_PASON);
        }
        return new CommandResult(output, CommandResultType.CHAT_PASON);
    }

    public boolean isExit() {
        return false;
    }
}
