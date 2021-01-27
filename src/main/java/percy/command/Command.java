package percy.command;
import java.io.IOException;

import percy.task.TaskList;
import percy.storage.Storage;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    abstract public String execute(TaskList list, Storage storage) throws IOException;
}
