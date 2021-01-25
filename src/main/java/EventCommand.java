import java.time.LocalDateTime;
import java.util.Date;

public class EventCommand extends AddCommand {

    private LocalDateTime timeWindow;

    public EventCommand(String taskDescription, LocalDateTime timeWindow) {
        super(taskDescription);
        this.timeWindow = timeWindow;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new EventTask(this.taskDescription, this.timeWindow);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }
}
