package command;

import component.Storage;
import component.TaskList;
import component.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
