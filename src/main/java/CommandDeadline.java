import java.time.LocalDateTime;

/**
 * Represents the Command for "deadline" for the Duke object. CommandDeadline helps to initiate creation and adding of
 * the Deadline Task with the specified attributes into the provided TaskList.
 */
public class CommandDeadline extends Command {
    private final String description;
    private final LocalDateTime dateTime;

    /**
     * Initialize the CommandDeadline object to represent the Deadline Task with the specified description and datetime.
     *
     * @param description Description of the Deadline Task.
     * @param dateTime Date and time the Deadline Task should be done by.
     */
    public CommandDeadline(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Creates a Deadline Task with the specified attributes and adds it into the provided TaskList. Then, save the
     * updated TaskList.
     *
     * @param tasks TaskList of Duke object, contains all the Tasks that were added.
     * @param storage Storage of Duke object, saves the TaskList into a text file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(description, dateTime);
        tasks.addTask(deadline);
        storage.save(tasks);
        return this.toDukeOutput() + "\n" + deadline.toString();
    }

    /**
     * Returns the string representation of the message to be printed by the Duke object's Ui.
     *
     * @return String representation of the message to be printed by the Duke object's Ui.
     */
    @Override
    public String toDukeOutput() {
        return "Roger that boss, I've added a new Deadline: ";
    }
}
