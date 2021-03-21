package antonio.command;

import java.time.LocalDate;

import antonio.TaskList;
import antonio.task.EventTask;
/**
 * Represents a command that adds Event tasks.
 */
public class AddEvent extends AddCommand {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a commands that adds Event tasks.
     * @param commandType Type of command.
     * @param description Description of the task.
     * @param eventDate Date of the event.
     * @param startTime Start time of the event.
     * @param endTime End time of the event
     */
    public AddEvent(String commandType, String description, LocalDate startDate, String startTime,
                    LocalDate endDate, String endTime) {
        super(commandType, description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new EventTask(description, taskID, 0, startDate, startTime, endDate, endTime);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }
}
