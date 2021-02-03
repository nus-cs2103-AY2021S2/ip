package duke.command;

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
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.getTasks().size() - 1 || index < 0) {
            return ui.showNotFound();
        }
        Task t = taskList.getTasks().get(index);
        t.markAsDone();
        return ui.showDone(t);
    }
}
