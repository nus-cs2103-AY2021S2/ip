package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private int position;

    /**
     * Creates a DoneCommand object to store the done command input from the user.
     * @param taskList the current list of Tasks.
     * @param ui the object in charge of printing user-friendly outputs.
     * @param storage the object in charge of writing to the local storage file.
     * @param position the position of the task in the taskList to mark as done.
     */
    public DoneCommand(TaskList taskList, Ui ui, Storage storage, int position) {
        super(taskList, ui, storage);
        this.position = position;
    }

    /**
     * Marks Task at previously specified position of tasklist as done, before printing a confirmation message.
     * @return message confirming that indicated task is set as done.
     */
    @Override
    public String execute() {
        this.taskList.setTaskDone(this.position);
        return "Nice! I've marked this task as done:\n" + this.taskList.getList().get(this.position);
    }
}
