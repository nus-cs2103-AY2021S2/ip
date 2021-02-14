package command;

import task.TaskManager;
import util.DukeException;

import java.util.HashMap;
import java.util.List;

public abstract class Command {
    public abstract void execute(TaskManager taskManager) throws DukeException;

    public abstract String getMessage();

    public boolean isQuitCommand() {
        return false;
    }
}