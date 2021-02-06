package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui);

    public abstract boolean isExit();
}
