package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    public abstract void executeAndPrint(TaskList list, int length) throws DukeException;
    public abstract boolean isExit();
}
