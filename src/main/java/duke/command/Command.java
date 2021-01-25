package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {
    protected String command;
    protected String description;
    protected String date;
    protected String output;

    public String getTaskInfo() {
        return String.format("%s %s %s", command, description, date);
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
