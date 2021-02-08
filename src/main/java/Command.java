import java.io.IOException;

public abstract class Command {
    protected String[] info;

    public abstract boolean isBye();

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
