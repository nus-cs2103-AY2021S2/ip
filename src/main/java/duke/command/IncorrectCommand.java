package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    public IncorrectCommand() {

    }
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showWrongCommandMessage();
    }

}
