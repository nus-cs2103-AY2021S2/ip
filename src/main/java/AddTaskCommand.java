import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    AddTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

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
