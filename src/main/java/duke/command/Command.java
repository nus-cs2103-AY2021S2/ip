package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    /** User command that is split by spaces */
    protected String[] commandSplit;

    public Command(String[] commandSplit) {
        this.commandSplit = commandSplit;
    }

    public abstract String execute(TaskList list) throws DukeException;
}
