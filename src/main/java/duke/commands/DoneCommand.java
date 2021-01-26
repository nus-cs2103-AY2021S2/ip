package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private int position;

    public DoneCommand(TaskList taskList, Ui ui, Storage storage, int position) {
        super(taskList, ui, storage);
        this.position = position;
    }

    /**
     * Marks Task at previously specified position of tasklist as done, before printing a confirmation message.
     */
    @Override
    public void execute() {
        this.taskList.setTaskDone(this.position);
        System.out.println("Nice! I've marked this task as done:");
        this.taskList.printTask(this.position);
    }
}
