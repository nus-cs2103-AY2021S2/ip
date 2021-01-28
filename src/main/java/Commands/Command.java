package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public abstract class Command {
    TaskList tasklist;
    Ui ui;
    Storage storage;

    Command(TaskList tasklist, Ui ui, Storage storage) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.storage = storage;
    }

    Command() { }

    public abstract void execute(TaskList t, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
