package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    Command(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public abstract void execute();

    /**
     * Returns signal to indicate if command is end of program.
     * @return boolean signal to indicate end of program.
     */
    public boolean isExit() {
        return false;
    }
}
