import java.io.IOException;

public abstract class Command {

    protected String type;
    protected String description;
    protected boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public abstract boolean isExit();
}
