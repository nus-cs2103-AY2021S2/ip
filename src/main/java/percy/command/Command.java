package percy.command;

import java.io.IOException;

import percy.storage.Storage;
import percy.task.TaskList;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract String execute(TaskList list, Storage storage) throws IOException;
}
