package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public class ListCommand extends Command {
    /**
     * Lists out all the tasks in taskList.
     * @param taskList
     * @param ui
     * @param storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showList(taskList);
    }
}
