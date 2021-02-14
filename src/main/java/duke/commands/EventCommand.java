package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.tasks.TaskList;
import duke.tasks.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String taskDescription;
    private LocalDate eventDate;

    public EventCommand(TaskList taskList, String taskDescription, LocalDate eventDate) {
        super(taskList);
        this.taskDescription = taskDescription;
        this.eventDate = eventDate;
    }
    public EventCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.taskDescription = "";
        this.eventDate = null;
    }

    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of an event cannot be empty.");
            }
            String taskDescription = this.getDescription(this.getUserInput(), "\n");
            LocalDate dueDate = this.getDueDate(this.getUserInput(), "/by");
            return new EventCommand(this.getTaskList(), taskDescription, dueDate);
        } catch (InsufficientArgumentsException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
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