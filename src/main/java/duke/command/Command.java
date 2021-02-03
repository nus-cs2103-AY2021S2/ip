package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract String execute(Storage storage, TaskList taskList, Ui ui, String command);
}
