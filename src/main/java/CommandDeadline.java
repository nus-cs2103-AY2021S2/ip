import java.time.LocalDateTime;

/**
 * Represents the Command for "deadline" for the Henchman object. CommandDeadline helps to initiate creation and adding
 * of the Deadline Task with the specified attributes into the provided TaskList.
 */
public class CommandDeadline extends Command {
    private final String DESCRIPTION;
    private final LocalDateTime DATE_TIME;

    /**
     * Initialize the CommandDeadline object to represent the Deadline Task with the specified description and datetime.
     *
     * @param description Description of the Deadline Task.
     * @param dateTime Date and time the Deadline Task should be done by.
     */
    public CommandDeadline(String description, LocalDateTime dateTime) {
        this.DESCRIPTION = description;
        this.DATE_TIME = dateTime;
    }

    /**
     * Creates a Deadline Task with the specified attributes and adds it into the provided TaskList. Then, save the
     * updated TaskList.
     *
     * @param tasks TaskList of Henchman object, contains all the Tasks that were added.
     * @param storage Storage of Henchman object, saves the TaskList into a text file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(DESCRIPTION, DATE_TIME);
        tasks.addTask(deadline);
        storage.save(tasks);
        return this.toHenchmanOutput() + "\n" + deadline.toString();
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Roger that boss, I've added a new Deadline: ";
    }
}
