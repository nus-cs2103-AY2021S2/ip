/**
 * Represents a command involving the completion of a task.
 */
public class DoneCommand extends Command {
    private int taskno;

    /**
     * Constructor for DoneCommand.
     * @param taskno Number corresponding to the completed task.
     */
    DoneCommand(int taskno) {
        this.taskno = taskno;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.set(this.taskno - 1, tasks.get(this.taskno - 1).done());
        ui.done(tasks.get(this.taskno - 1).toString());
        storage.savetasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Checks the equivalence of DoneCommand this and Object obj.
     * If obj is an instance of the DoneCommand class and all attributes are equivalent, it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DoneCommand) {
            DoneCommand dc = (DoneCommand) obj;
            return dc.taskno == this.taskno;
        }
        return false;
    }
}
