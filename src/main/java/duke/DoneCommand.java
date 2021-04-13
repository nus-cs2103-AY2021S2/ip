package duke;

/**
 * Done command for Duke.
 */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Constructs new done command.
     *
     * @param taskNum The task number to be set as done.
     */
    public DoneCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Marks a task done of a specific numbering.
     * Prints marked message and update drive.
     *
     * @param tl task list.
     * @param ui object for user interface.
     * @param storage object to store and load task list.
     * @throws DukeException When command is wrong in printTaskMsg().
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        Task t = tl.get(taskNum - 1);
        t.finishTask();
        tl.set(taskNum - 1, t);
        storage.save(tl.toString());
        return ui.printTaskMsg(t, tl, "done");
    }
}

