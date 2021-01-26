package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int position;

    public DeleteCommand(TaskList taskList, Ui ui, Storage storage, int position) {
        super(taskList, ui, storage);
        this.position = position;
    }

    /**
     * Deletes Task at the previously specified position in the taskList.
     * Thereafter, prints confirmation and remaining number of tasks.
     */
    @Override
    public void execute() {
        System.out.println("Noted. I've removed this task:");
        this.taskList.printTask(this.position);
        this.taskList.deleteTask(this.position);
        this.taskList.printNumTasksInList();
    }
}
