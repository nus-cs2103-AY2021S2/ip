package project.command;

import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        return ui.showFormatResponse("  Aww hope to see you soon, goodbye!...");
    }
}
