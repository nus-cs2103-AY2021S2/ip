package duke.command;

import java.time.LocalDate;
import duke.TaskList;
import duke.task.DeadlineTask;

public class AddDeadline extends AddCommand {

    private LocalDate deadline;
    private String time;

    /**
     * Constructor for commands.AddCommand class duke.command name and description.
     *
     * @param commandType Type of command
     * @param description Description of the duke.command.
     */
    public AddDeadline(String commandType, String description, LocalDate deadline, String time) {
        super(commandType, description);
        this.deadline = deadline;
        this.time = time;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new DeadlineTask(description, taskID, 0, deadline, time);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }
}
