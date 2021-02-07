package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;

    Command(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public abstract String execute();
}
