package command;

import task.TaskManager;
import util.DukeException;

public abstract class Command {
    public abstract void execute(TaskManager taskManager) throws DukeException;

    public abstract String getMessage();

    public boolean isQuitCommand() {
        return false;
    }
}