package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
