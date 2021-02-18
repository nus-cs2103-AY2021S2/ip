package command;

import task.TaskManager;
import util.DukeException;

public abstract class Command {
    public abstract String execute(TaskManager taskManager) throws DukeException;

    public boolean isQuitCommand() {
        return false;
    }
}