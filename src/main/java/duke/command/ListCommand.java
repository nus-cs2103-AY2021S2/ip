package duke.command;

import duke.DukeResponse;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;


public class ListCommand extends Command {
    /**
     * Lists out all the tasks in taskList.
     * @param taskList
     * @param ui
     * @param storage
     * @return DukeResponse
     */
    public DukeResponse execute(TaskList taskList, Ui ui, Storage storage) {
        return new DukeResponse(ui.showList(taskList));
    }
}
