package duke.command;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;

/**
 * Interface for all command, which have an execute method that performs an action
 */

public interface Command {
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage);
}
