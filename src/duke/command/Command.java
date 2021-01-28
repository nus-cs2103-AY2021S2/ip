package duke.command;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;

public interface Command {
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage);
}
