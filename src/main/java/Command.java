import java.util.ArrayList;

public abstract class Command {
    public abstract String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException;

    public abstract boolean isRunning();
}
