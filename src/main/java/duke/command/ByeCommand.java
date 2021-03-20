package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {

    }
    public String execute(TaskList tasks, Storage storage) {
        return "Bye";
    }

}
