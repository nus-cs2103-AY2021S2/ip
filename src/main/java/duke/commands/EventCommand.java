package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private TaskList taskList;
    private String taskDescription;
    private LocalDate eventDate;

    public EventCommand(TaskList taskList, String taskDescription, LocalDate eventDate) {
        super(taskList);
        this.taskDescription = taskDescription;
        this.eventDate = eventDate;
    }

    public TaskList execute() {
        TaskList taskList = this.getTaskList();
        Event event = new Event(this.taskDescription, this.eventDate);
        taskList = taskList.add(event);
        return taskList;
    }

    public String toString() {
        String message = "Got it. I've added this task:\n";
        Event event = new Event(this.taskDescription, this.eventDate);
        message += event + "\n";
        message += "Now you have " + this.getTaskList().size() + " tasks in the list.";
        return message;
    }
}