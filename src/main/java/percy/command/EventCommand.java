package percy.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import percy.storage.Storage;
import percy.task.Event;
import percy.task.Task;
import percy.task.TaskList;
import percy.ui.Ui;


public class EventCommand extends Command {
    public static final String COMMAND = "event";
    public static final String DATE_TIME_PREFIX = " /at ";
    public static final ArrayList<String> USAGE_GUIDE = new ArrayList<>(List.of(
            "event: Adds a task that starts at a specific date and time and ends at a specific date and time.",
            "Parameters: TASK_DESCRIPTION /at DATE(yyyy-MM-dd) START_TIME(HHmm)-END_TIME(HHmm)",
            "Example: event project meeting /at 2021-01-27 1800-2000"));

    private final String eventDescription;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs event command.
     * @param eventDescription description of event
     * @param date date of event
     * @param time time of event
     */
    public EventCommand(String eventDescription, LocalDate date, LocalTime time) {
        super(false);
        this.eventDescription = eventDescription;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) throws IOException {
        Task event = new Event(eventDescription, date, time);

        taskList.addTaskToList(event);
        storage.save(taskList);
        return Ui.makeAddMsg(event, taskList);
    }
}
