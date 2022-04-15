package project.command;

import project.common.PrintedText;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        if (taskList.hasTasks()) {
            return ui.showList(taskList);
        } else {
            return PrintedText.EMPTY_TASKLIST_ERROR.toString();
        }
    }
}
