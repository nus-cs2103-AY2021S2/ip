package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The Command class denotes a command to the Duke chat bot.
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
     * @param taskList A list of recorded tasks.
     * @param ui       A user interface.
     * @param storage  A list of recorded user inputs data.
     * @return         The message replied by Duke chat bot.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Determine whether the command will terminate the program.
     * @return   A signal to terminate the program.
     */
    public abstract boolean isExit();
}
