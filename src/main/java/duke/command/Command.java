package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    /** User command that is split by spaces */
    protected String[] commandSplit;

    public Command(String[] commandSplit) {
        this.commandSplit = commandSplit;
    }

    public boolean isExit() {
        return this.commandSplit.length == 1 && this.commandSplit[0].equals("bye");
    }

    public abstract void execute(TaskList list) throws DukeException;
}
