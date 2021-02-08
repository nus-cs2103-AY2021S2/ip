package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

public interface Command {
    public boolean isExit();

    public void execute(TaskList tasks, Ui ui, Storage storage);
}
