package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, TaskStorage storage);
}
