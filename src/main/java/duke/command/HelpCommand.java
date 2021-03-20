package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    /**
     * Executes the help command.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     */
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showHelpMessage();
    }
}
