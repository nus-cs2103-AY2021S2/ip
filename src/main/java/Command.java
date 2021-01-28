import java.io.IOException;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    public void toggleExit() {
        this.isExit = !this.isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
