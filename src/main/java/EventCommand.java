import java.time.LocalDate;

/**
 * Handles the addition of Events by the user.
 */
public class EventCommand extends AddCommand {
    private String taskString;
    private LocalDate date;

    EventCommand(String taskString, LocalDate date, TaskList tasks) {
        super.tasks = tasks;
        this.taskString = taskString;
        this.date = date;
    }

    /**
     * Returns a string representation of the Event object just added.
     *
     * @return A string which represents the Event just added.
     */
    public String getString() {
        Event event = new Event(taskString, date);
        tasks.add(event);
        return Ui.showAddText() + event.toString() + tasks.getSizeString();
    }
}
