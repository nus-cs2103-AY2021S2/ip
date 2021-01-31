import java.io.IOException;

/**
 * An abstract class that represents all commands.
 */
public abstract class Command {
    void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
    }

    public boolean isExit() {
        return false;
    }
}
