import java.time.LocalDateTime;

/**
 * Represents the Command for "event" for the Henchman object. CommandEvent helps to initiate creation and adding of
 * the Event Task with the specified attributes into the provided TaskList.
 */
public class CommandEvent extends Command {
    private final String DESCRIPTION;
    private final LocalDateTime DATE_TIME;

    /**
     * Initialize the CommandEvent object to represent the Event Task with the specified description and datetime.
     *
     * @param description Description of the Event Task.
     * @param dateTime Date and time the Event Task will occur on.
     */
    public CommandEvent(String description, LocalDateTime dateTime) {
        this.DESCRIPTION = description;
        this.DATE_TIME = dateTime;
    }

    /**
     * Creates an Event Task with the specified attributes and adds it into the provided TaskList. Then, save the
     * updated TaskList.
     *
     * @param tasks TaskList of Henchman object, contains all the Tasks that were added.
     * @param storage Storage of Henchman object, saves the TaskList into a text file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event event = new Event(DESCRIPTION, DATE_TIME);
        tasks.addTask(event);
        storage.save(tasks);
        return this.toHenchmanOutput() + "\n" + event.toString();
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Roger that boss, I've added a new Event: ";
    }
}
