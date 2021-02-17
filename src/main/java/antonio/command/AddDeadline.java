package antonio.command;

import java.time.LocalDate;

import antonio.TaskList;
import antonio.task.DeadlineTask;
/**
 * Represents a command that adds Deadline tasks.
 */
public class AddDeadline extends AddCommand {

    private LocalDate deadline;
    private String time;

    /**
     * Constructs a commands that adds Deadline tasks.
     * @param commandType Type of command.
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     * @param time Deadline time of the task.
     */
    public AddDeadline(String commandType, String description, LocalDate deadline, String time) {
        super(commandType, description);
        this.deadline = deadline;
        this.time = time;
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
        newTask = new DeadlineTask(description, taskID, 0, deadline, time);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }
}
