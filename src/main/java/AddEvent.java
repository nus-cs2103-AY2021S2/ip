import java.time.LocalDate;

public class AddEvent extends AddCommand {

    private LocalDate eventDate;
    private String startTime;
    private String endTime;

    /**
     * Constructor for AddCommand class command name and description.
     *
     * @param commandType
     * @param description Description of the command.
     */
    public AddEvent(String commandType, String description, LocalDate eventDate, String startTime, String endTime) {
        super(commandType, description);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new EventTask(description, taskID, 0, eventDate, startTime, endTime);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }
}
