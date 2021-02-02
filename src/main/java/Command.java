import java.io.IOException;

/**
 * An abstract class that represents all commands.
 */
public abstract class Command {

    String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        return "";
    }

    public boolean isExit() {
        return false;
    }
}
