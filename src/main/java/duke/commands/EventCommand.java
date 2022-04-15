package duke.commands;

import duke.parser.DateCommandException;
import duke.parser.DuplicateException;
import duke.parser.InsufficientArgumentsException;
import duke.tasks.TaskList;
import duke.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * An EventCommand is a command that adds an event to the task list.
 */
public class EventCommand extends DateCommand {
    private static final String DELIMITER = "/at";

    public EventCommand(TaskList taskList, String taskDescription, LocalDate eventDate) {
        super(taskList, taskDescription, eventDate);
    }

    public EventCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
    }

    @Override
    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of an event cannot be empty.");
            }
            String taskDescription = this.getDescription(this.getUserInput(), DELIMITER);
            this.checkValidity(this.getUserInput(), DELIMITER);
            this.getTaskList().hasDuplicate(taskDescription);
            LocalDate dueDate = this.extractDateFromCommand(this.getUserInput(), DELIMITER);
            return new EventCommand(this.getTaskList(), taskDescription, dueDate);
        } catch (InsufficientArgumentsException | DuplicateException
                | DateCommandException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        } catch (DateTimeParseException e) {
            return new ErrorCommand(this.getTaskList(),
                    "Please enter the date in the format yyyy-mm-dd "
                            + "(e.g. 2021-03-19)");
        }
    }

    @Override
    public TaskList execute() {
        TaskList taskList = this.getTaskList();
        int initialSize = taskList.size();
        Event event = new Event(this.taskDescription, this.date);
        taskList = taskList.add(event);
        assert(initialSize + 1 == taskList.size()); // ensure that event is properly added
        return taskList;
    }

    @Override
    public String toString() {
        String message = "Got it. I've added this task:\n";
        Event event = new Event(this.taskDescription, this.date);
        message += event + "\n";
        message += "Now you have " + (this.getTaskList().size() + 1) + " tasks in the list.";
        return message;
    }
}