package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Abstract command class that can be inherited by various commands
 */
public abstract class Command {
    protected String commandType;
    protected String commandDetails;
    protected int index;
    protected String dateTime;
    protected String outputMessage;

    /**
     * Used for testing in Junit
     *
     * @return Command name, details of task and date (when applicable)
     */
    public String getTaskDetails() {
        return String.format("%s %s %s", commandType, commandDetails, dateTime);
    }

    /**
     * Execute the command accordingly
     *
     * @param tasks TaskList
     * @param storage Instance of Storage
     * @throws DukeException If there is an invalid input value from the user
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public String getMessage() {
        return this.outputMessage;
    }
}
