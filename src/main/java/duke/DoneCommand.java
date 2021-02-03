package duke;

public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Constructer for done command.
     *
     * @param taskNum The task number to be set as done.
     */
    public DoneCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Marks a task done of a specfic numbering.
     * Print marked message and update drive.
     *
     * @param tl task list.
     * @param ui object for user interface.
     * @param storage objec to store and load task list.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Task t = tl.get(taskNum - 1);
        t = t.finishTask();
        tl.set(taskNum - 1, t);
        ui.printMarkedDone(t);
        Storage.save(tl.toString());
    }
}
