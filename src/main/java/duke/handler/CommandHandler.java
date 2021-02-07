package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/**
 * Interface for command execution.
 */
public interface CommandHandler {
    public void execute(Ui ui, Storage storage, TaskList taskList);
}
