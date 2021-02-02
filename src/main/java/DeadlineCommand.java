/**
 * Represents a command involving the addition of a task with a deadline.
 */
public class DeadlineCommand extends Command {
    private String name;
    private String deadline;

    /**
     * Constructor for DeadlineCommand.
     * @param name Name of task.
     * @param deadline Deadline of task.
     */
    DeadlineCommand(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(this.name, this.deadline);
        tasks.add(task);
        ui.addtask(task.toString(), tasks.size());
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
     * Checks the equivalence of DeadlineCommand this and Object obj.
     * If obj is an instance of the DeadlineCommand class and all attributes are equivalent, it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeadlineCommand) {
            DeadlineCommand dlc = (DeadlineCommand) obj;
            return dlc.name.equals(this.name) && dlc.deadline.equals(this.deadline);
        }
        return false;
    }
}
