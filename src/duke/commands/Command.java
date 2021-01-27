package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract CommandResult execute(TaskList tasks, Storage storage);
}
