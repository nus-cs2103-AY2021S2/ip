/**
 * Represents a command involving the addition of a task which is an event.
 */
public class EventCommand extends Command {
    private String event;
    private String date;

    /**
     * Constructor for EventCommand.
     * @param event Name of task.
     * @param date Date of task.
     */
    EventCommand(String event, String date) {
        this.event = event;
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(this.event, this.date);
        tasks.add(task);
        storage.saveTasks(tasks);
        return ui.addTask(task.toString(), tasks.size());
    }

    /**
     * Checks the equivalence of EventCommand this and Object obj.
     * If obj is an instance of the EventCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof EventCommand) {
            EventCommand eventCommand = (EventCommand) obj;
            return eventCommand.event.equals(this.event) && eventCommand.date.equals(this.date);
        }
        return false;
    }
}
