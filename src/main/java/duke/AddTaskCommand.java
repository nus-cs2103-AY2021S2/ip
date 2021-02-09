package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    public AddTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct To-do command.
     * If it is, it adds the To-do to the task list and prints the Add task message.
     * Otherwise, it prints the exception faced.
     *
     * @return String of to-do task or error faced
     */
    private String addTodo() {
        try {
            if (parser.canParseTodo(input)) {
                String description = parser.parseTodoDescription(input);
                Task task = new Todo(description);
                taskList.addTask(task);
                return ui.getAddTask(task, taskList.getSize());
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Checks if the user input is formatted into a correct Deadline command.
     * If it is, it adds the Deadline to the task list and prints the Add task message.
     * Otherwise, it prints the exception faced.
     *
     * @return String of deadline task or error faced
     */
    private String addDeadline() {
        try {
            if (parser.canParseDeadline(input)) {
                try {
                    LocalDateTime dateTime = parser.parseDeadlineDate(input);
                    String description = parser.parseDeadlineDescription(input);
                    Deadline task = new Deadline(dateTime, description);
                    taskList.addTask(task);
                    return ui.getAddTask(task, taskList.getSize());
                } catch (DateTimeParseException e) {
                    return ui.getError(new WrongFormatDukeException(command));
                }
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Checks if the user input is formatted into a correct Event command.
     * If it is, it adds the Event to the task list and prints the Add task message.
     * Otherwise, it prints the exception faced.
     *
     * @return String of event task or error faced.
     */
    private String addEvent() {
        try {
            if (parser.canParseEvent(input)) {
                try {
                    LocalDateTime startDateTime = parser.parseEventStartDate(input);
                    LocalDateTime endDateTime = parser.parseEventEndDate(input);
                    String description = parser.parseEventDescription(input);
                    Event task = new Event(startDateTime, endDateTime, description);
                    taskList.addTask(task);
                    return ui.getAddTask(task, taskList.getSize());
                } catch (DateTimeParseException e) {
                    return ui.getError(new WrongFormatDukeException(command));
                }
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the add task command.
     *
     * @return String of task executed.
     */
    @Override
    public String execute() {
        if (command.equals("todo")) {
            return addTodo();
        }
        if (command.equals("deadline")) {
            return addDeadline();
        }
        if (command.equals("event")) {
            return addEvent();
        }
        return "Something went wrong";
    }
}
