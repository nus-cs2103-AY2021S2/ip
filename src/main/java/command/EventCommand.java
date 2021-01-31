package command;

import java.time.LocalDateTime;

import mike.TaskList;
import task.EventTask;

public class EventCommand extends AddCommand {

    private final LocalDateTime timeWindow;

    /**
     * Constructor for the EventCommand object that includes a LocalDateTime object to represent the deadline of
     * the task
     * @param taskDescription Description of the task to be added
     * @param timeWindow timeWindow of the task to be added
     */
    public EventCommand(String taskDescription, LocalDateTime timeWindow) {
        super(taskDescription);
        this.timeWindow = timeWindow;
    }

    /**
     * Execute command and adds deadline task to the provided taskList
     * @param taskList TaskList object to add the task to
     * @return TaskList object after adding task to the list
     */
    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new EventTask(this.taskDescription, this.timeWindow);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }
}
