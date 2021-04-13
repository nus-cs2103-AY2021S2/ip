package duke;

/**
 * Delete command for Duke.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructs a delete command.
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
     * @throws DukeException When command is wrong in printTaskMsg().
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        Task t = tl.remove(taskNum - 1);
        storage.save(tl.toString());
        return ui.printTaskMsg(t, tl, "delete");
    }
}

