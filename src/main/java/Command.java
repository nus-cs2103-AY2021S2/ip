import java.io.IOException;

public abstract class Command {
    public String[] info;

    public abstract boolean isBye();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
