package Command;
import Oracle.TaskList;
import Oracle.Ui;
import Entry.Event;

public class EventCommand implements Command {
    private final String taskDescription;
    private final String taskTime;

    /**
     * @param taskDescription description of the Event
     * @param taskTime string to be formatted into a LocalDateTime
     */
    public EventCommand(String taskDescription, String taskTime) {
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
    }

    /** Creates a new Event
     * @param ui helper to interact with user
     * @param tasks we add the new created event here
     * @return true
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        try {
            tasks.add(new Event(this.taskDescription, this.taskTime));
        } catch (CommandFormatException e) {
            ui.showFormatException("EventCommand");
            return true;
        }
        ui.showNewTask(tasks.size(), tasks.get(tasks.size()-1));
        return true;
    }
}
