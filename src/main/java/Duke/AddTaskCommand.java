package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    AddTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct To-do command.
     * If it is, it adds the To-do to the task list and prints the Add task message.
     * Otherwise, it prints the exception faced.
     */
    private void addTodo() {
        try {
            if (parser.isCorrectTodo(input)) {
                String description = parser.parseTodoDescripton(input);
                Task task = new Todo(description);
                taskList.addTask(task);
                ui.printAddTask(task, taskList.size());
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    /**
     * Checks if the user input is formatted into a correct Deadline command.
     * If it is, it adds the Deadline to the task list and prints the Add task message.
     * Otherwise, it prints the exception faced.
     */
    private void addDeadline() {
        try {
            if (parser.isCorrectDeadline(input)) {
                try {
                    LocalDateTime dateTime = parser.parseDeadlineDate(input);
                    String description = parser.parseDeadlineDescription(input);
                    Deadline task = new Deadline(dateTime, description);
                    taskList.addTask(task);
                    ui.printAddTask(task, taskList.size());
                } catch (DateTimeParseException e) {
                    ui.printError(new WrongFormatDukeException(command));
                }
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    /**
     * Checks if the user input is formatted into a correct Event command.
     * If it is, it adds the Event to the task list and prints the Add task message.
     * Otherwise, it prints the exception faced.
     */
    private void addEvent() {
        try {
            if (parser.isCorrectEvent(input)) {
                try {
                    LocalDateTime startDateTime = parser.parseEventStartDate(input);
                    LocalDateTime endDateTime = parser.parseEventEndDate(input);
                    String description = parser.parseEventDescription(input);
                    Event task = new Event(startDateTime, endDateTime, description);
                    taskList.addTask(task);
                    ui.printAddTask(task, taskList.size());
                } catch (DateTimeParseException e) {
                    ui.printError(new WrongFormatDukeException(command));
                }
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    @Override
    public void execute() {
        if (command.equals("todo")) {
            addTodo();
        } else if (command.equals("deadline")) {
            addDeadline();
        } else if (command.equals("event")) {
            addEvent();
        }
    }
}
