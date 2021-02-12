package project.command;

import project.common.PrintedText;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        return ui.showFormatResponse(PrintedText.HELP);
    }
}
