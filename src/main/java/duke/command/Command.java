package duke.command;

import duke.CallbackFunction;
import duke.DukeException;
import duke.TaskList;
import javafx.util.Pair;

public abstract class Command {
    /** User command that is split by spaces */
    protected String[] commandSplit;

    public Command(String[] commandSplit) {
        this.commandSplit = commandSplit;
    }

    public abstract Pair<String, CallbackFunction> execute(TaskList list) throws DukeException;
}
