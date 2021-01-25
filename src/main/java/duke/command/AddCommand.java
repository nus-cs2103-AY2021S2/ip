package duke.command;

import duke.task.Task;
import duke.TaskList;
/**
 * Represents a duke.command that adds tasks.
 */
public abstract class AddCommand implements Command {

    protected String commandType;
    protected String description;
    protected Task newTask;
    protected int numTasks;

    /**
     * Constructor for commands.AddCommand class duke.command name and description.
     * @param commandType Name of the command.
     * @param description Description of the duke.command.
     */
    public AddCommand(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Returns positive if duke.command terminates chat bot.
     * @return True if this duke.command terminates chat bot.
     */
    public boolean shouldExit() {
        return false;
    }

    public abstract TaskList execute(TaskList taskList);

    public String getResponse() {
        return "Got it. I've added this duke.task:\n  ";
    }

}
