package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

public abstract class Command {
    protected final String taskDescription;

    public Command(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
