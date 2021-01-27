package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

public abstract class Command {
    public static boolean EXECUTION_SUCCESS = true;
    public static boolean EXECUTION_FAIL = false;
    public abstract boolean execute(Ui ui, TaskList tasks, Storage storage);
}
