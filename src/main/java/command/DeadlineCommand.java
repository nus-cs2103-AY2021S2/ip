package command;
import mike.TaskList;
import task.*;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {

    private final LocalDateTime deadline;

    public DeadlineCommand(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new DeadlineTask(this.taskDescription, this.deadline);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }
}
