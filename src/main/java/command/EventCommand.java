package command;
import mike.TaskList;
import task.*;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand {

    private final LocalDateTime timeWindow;

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
