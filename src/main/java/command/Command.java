package command;

import task.TaskManager;
import util.DukeException;

import java.util.HashMap;
import java.util.List;

public interface Command {
    void execute(TaskManager taskManager) throws DukeException;

    String getMessage();
}
