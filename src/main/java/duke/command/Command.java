package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Abstract class to be inherited by different commands
 */
public abstract class Command {
    protected String command;
    protected String description;
    protected String date;
    protected String output;

    /**
     * Used for JUnit test
     *
     * @return Information of command, description, date
     */
    public String getTaskInfo() {
        return String.format("%s %s %s", command, description, date);
    }

    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;
    public abstract boolean isExit();

    public String getResponse() {
        return output;
    }
}
