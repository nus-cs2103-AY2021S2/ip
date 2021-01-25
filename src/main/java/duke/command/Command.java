package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected final String taskDescription;

    public Command (String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
