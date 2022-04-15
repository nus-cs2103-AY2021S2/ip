package duke.commands;

import duke.parser.DateCommandException;
import duke.parser.DuplicateException;
import duke.parser.InsufficientArgumentsException;
import duke.tasks.TaskList;
import duke.tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * DeadlineCommand is a command that adds a Deadline to the task list.
 */
public class DeadlineCommand extends DateCommand {
    private static final String DELIMITER = "/by";

    public DeadlineCommand(TaskList taskList, String taskDescription, LocalDate dueDate) {
        super(taskList, taskDescription, dueDate);
    }

    public DeadlineCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
    }

    @Override
    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of a deadline cannot be empty.");
            }
            String taskDescription = this.getDescription(this.getUserInput(), DELIMITER);
            this.checkValidity(this.getUserInput(), DELIMITER);
            this.getTaskList().hasDuplicate(taskDescription);
            LocalDate dueDate = this.extractDateFromCommand(this.getUserInput(), DELIMITER);
            return new DeadlineCommand(this.getTaskList(), taskDescription, dueDate);
        } catch (InsufficientArgumentsException | DuplicateException | DateCommandException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        } catch (DateTimeParseException e) {
            return new ErrorCommand(this.getTaskList(),
                    "Please enter the date in the format yyyy-mm-dd "
                            + "(e.g. 2021-03-19)");
        }
    }

    @Override
    public TaskList execute() {
        LocalDate dueDate = this.date;
        String taskDescription = this.taskDescription;
        Deadline deadline = new Deadline(taskDescription, dueDate);
        TaskList taskList = this.getTaskList();
        int initialSize = taskList.size();
        taskList = taskList.add(deadline);
        assert(initialSize + 1 == taskList.size()); // ensure that event is properly added
        return taskList;
    }

    @Override
    public String toString() {
        String message = "Got it. I've added this task:\n";
        Deadline deadline = new Deadline(this.taskDescription, this.date);
        message += deadline.toString() + "\n";
        message += "Now you have " + (this.getTaskList().size() + 1) + " tasks in the list.\n";
        return message;
    }
}