import java.time.LocalDateTime;

/**
 * Represents the Command for "event" for the Duke object. CommandEvent helps to initiate creation and adding of
 * the Event Task with the specified attributes into the provided TaskList.
 */
public class CommandEvent extends Command {
    private final String description;
    private final LocalDateTime dateTime;

    /**
     * Initialize the CommandEvent object to represent the Event Task with the specified description and datetime.
     *
     * @param description Description of the Event Task.
     * @param dateTime Date and time the Event Task will occur on.
     */
    public CommandEvent(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Creates an Event Task with the specified attributes and adds it into the provided TaskList. Then, save the
     * updated TaskList.
     *
     * @param tasks TaskList of Duke object, contains all the Tasks that were added.
     * @param ui Ui of Duke object, prints the message of CommandEvent and Event Task.
     * @param storage Storage of Duke object, saves the TaskList into a text file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event event = new Event(description, dateTime);
        tasks.addTask(event);
        storage.save(tasks);
        return this.toDukeOutput() + "\n" + event.toString();
    }

    /**
     * Returns the string representation of the message to be printed by the Duke object's Ui.
     *
     * @return String representation of the message to be printed by the Duke object's Ui.
     */
    @Override
    public String toDukeOutput() {
        return "Roger that boss, I've added a new Event: ";
    }
}
