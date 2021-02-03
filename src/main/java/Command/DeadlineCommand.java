package Command;
import Oracle.TaskList;
import Oracle.Ui;
import Entry.Deadline;

public class DeadlineCommand implements Command {
    private final String taskDescription;
    private final String taskDeadline;

    /**
     * @param taskDescription description of the deadline
     * @param taskDeadline string to be formatted into a LocalDateTime
     */
    public DeadlineCommand(String taskDescription, String taskDeadline) {
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
    }

    /** Creates a new Deadline
     * @param ui helper to interact with user
     * @param tasks we add the new created deadline here
     * @return true
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        try {
            tasks.add(new Deadline(this.taskDescription, this.taskDeadline));
        } catch (CommandFormatException e) {
            ui.showFormatException("EventCommand");
            return true;
        }
        ui.showNewTask(tasks.size(), tasks.get(tasks.size() - 1));
        return true;
    }
}
