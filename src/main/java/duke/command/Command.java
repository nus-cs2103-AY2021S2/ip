package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The Command class denotes a command to the RoboBot chat bot.
 */
public abstract class Command {
    protected final String taskDescription;

    /**
     * Constructs a command
     * @param taskDescription The description of the task.
     */
    public Command(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executing the command
     * @param taskList The list of recorded tasks.
     * @param ui       The user interface.
     * @param storage  The list of recorded user inputs data.
     * @return         The message replied by RoboBot chat bot.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
