package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExceptionCommandNotFound;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.storage.FileLoader;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class DukeCommand {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeException;
}
