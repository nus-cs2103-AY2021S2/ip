package duke.command;

import duke.DukeResponse;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int index;

    /**
     * Creates a DoneCommand which marks the associated task with index as done when executed.
     * @param index
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the associated task with index as done.
     * @param taskList
     * @param ui
     * @param storage
     * @return DukeResponse
     */
    public DukeResponse execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.getTasks().size() - 1 || index < 0) {
            return new DukeResponse(ui.showNotFound());
        }
        assert index >= 0 && index <= taskList.getTasks().size() - 1 : "index should be in valid range";
        Task t = taskList.getTasks().get(index);
        t.markAsDone();
        return new DukeResponse(ui.showDone(t));
    }
}
