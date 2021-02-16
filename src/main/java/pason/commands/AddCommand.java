package pason.commands;

import pason.storage.Storage;
import pason.tasks.Task;
import pason.tasks.TaskList;

public class AddCommand extends Command {
    protected Task task;

    /**
     * Initialises the AddCommand.
     */
    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    /**
     * Executes the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null.";
        tasks.addTask(task);
        return new CommandResult("Done! I've added a new task:\n\t"
                + task + "\nNow there are " + tasks.getTasks().size()
                + " tasks in your list.", CommandResultType.CHAT_PASON);
    }

    public boolean isExit() {
        return false;
    }
}
