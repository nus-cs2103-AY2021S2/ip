package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand which adds the associated task when executed.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the associated task to taskList.
     * @param taskList
     * @param ui
     * @param storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTasks().add(this.task);
        return ui.showAdd(this.task, taskList);
    }
}
