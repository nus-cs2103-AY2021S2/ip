package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;

    Command(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public abstract String execute();

    /**
     * Returns signal to indicate if command is end of program.
     * @return boolean signal to indicate end of program.
     */
    public boolean isExit() {
        return false;
    }
}
