package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {
    boolean isExit();
    void execute(TaskList tasks, Ui ui, Storage storage);
}
