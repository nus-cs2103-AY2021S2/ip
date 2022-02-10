package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class PrintCommand extends Command {
    private String message;

    public PrintCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.message;
    }
}
