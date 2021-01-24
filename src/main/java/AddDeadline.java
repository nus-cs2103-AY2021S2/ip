import java.time.LocalDate;

public class AddDeadline extends AddCommand {

    private LocalDate deadline;
    private int time;

    /**
     * Constructor for AddCommand class command name and description.
     *
     * @param commandType
     * @param description Description of the command.
     */
    public AddDeadline(String commandType, String description, LocalDate deadline, int time) {
        super(commandType, description);
        this.deadline = deadline;
        this.time = time;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new DeadlineTask(description, taskID, deadline, time);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }
}
