import java.time.LocalDate;

/**
 * Handles the addition of Deadline objects.
 */
public class DeadlineCommand extends AddCommand {
    private String taskString;
    private LocalDate date;

    /**
     * Creates and initializes a DeadlineCommand to add a Deadline Object
     * @param taskString The description of the Deadline object.
     * @param date The date which the Deadline must meet.
     * @param tasks List that stores all tasks.
     */
    DeadlineCommand(String taskString, LocalDate date, TaskList tasks) {
        super.tasks = tasks;
        this.taskString = taskString;
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline object just added.
     *
     * @return A string which represents the Deadline just added.
     */
    public String getString() {
        Deadline deadline = new Deadline(taskString, date);
        tasks.add(deadline);
        return deadline.toString();
    }
}
