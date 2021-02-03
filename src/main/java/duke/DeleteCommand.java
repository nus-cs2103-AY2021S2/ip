package duke;

public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Construct a delete command.
     *
     * @param taskNum The task number to delete in task list.
     */
    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Deletes task from list of matching task-numbering.
     * Then prints delete message and update drive.
     *
     * @param tl task list.
     * @param ui object for user interface.
     * @param storage storage for tasks.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Task t = tl.remove(taskNum - 1);
        ui.printDeletedTask(t, tl);
        storage.save(tl.toString());
    }
}
