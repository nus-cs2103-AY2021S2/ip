package duke.commands;

import duke.parser.DuplicateException;
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
            String taskDescription = this.getDescription(this.getUserInput(), "/at");
            this.getTaskList().hasDuplicate(taskDescription);
            LocalDate dueDate = this.getDueDate(this.getUserInput(), "/at");
            return new EventCommand(this.getTaskList(), taskDescription, dueDate);
        } catch (InsufficientArgumentsException | DuplicateException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    public TaskList execute() {
        TaskList taskList = this.getTaskList();
        int initialSize = taskList.size();
        Event event = new Event(this.taskDescription, this.eventDate);
        taskList = taskList.add(event);
        assert(initialSize + 1 == taskList.size()); // ensure that event is properly added
        return taskList;
    }

    public String toString() {
        String message = "Got it. I've added this task:\n";
        Event event = new Event(this.taskDescription, this.eventDate);
        message += event + "\n";
        message += "Now you have " + (this.getTaskList().size() + 1) + " tasks in the list.";
        return message;
    }
}