package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Abstract class to process commands given by user.
 */
public abstract class Command {

    /**
     * Process various commands given by user.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui, String command);
}
