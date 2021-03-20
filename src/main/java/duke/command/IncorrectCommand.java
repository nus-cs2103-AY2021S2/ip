package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    public IncorrectCommand() {
    }

    /**
     * Executes an incorrect command.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     */
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showWrongCommandMessage();
    }
}
