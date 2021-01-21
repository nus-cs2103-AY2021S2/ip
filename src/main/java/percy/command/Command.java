package percy.command;
import java.io.IOException;

import percy.task.TaskList;
public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    abstract public String execute(TaskList list) throws IOException;
}
