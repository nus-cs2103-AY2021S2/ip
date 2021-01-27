package duke.command;

/**
 * Creates a Deadline task.
 */
public class DeadlineCommand extends Command{
    String description;
    String deadline;

    public DeadlineCommand(String description, String deadline) {
        super("deadline");
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns description of deadline task.
     * @return deadline task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns deadline of task.
     * @return task deadline
     */
    public String getDeadline() {
        return deadline;
    }

    @Override
    public void run() {

    }
}
