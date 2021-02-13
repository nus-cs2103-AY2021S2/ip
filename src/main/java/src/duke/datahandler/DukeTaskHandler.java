package duke.datahandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.enums.Command;
import duke.enums.TaskType;
import duke.exception.TaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class DukeTaskHandler {
    private Task newTask;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public DukeTaskHandler(Parser parser, Command taskType) throws TaskException {
        if (parser.hasDescriptionError()) {
            Parser.exception("The is no description, please enter description.");
        } else if (parser.hasDateError()) {
            Parser.exception("Date is either invalid or not entered, please rectify.");
        }

        TaskType typeOfTask = TaskType.valueOf(taskType.toString());
        switch (typeOfTask) {
            case DEADLINE:
                newTask = new Deadline(parser.getDescription(), LocalDate.parse(parser.getDate(), FORMATTER));
                break;
            case EVENT:
                newTask = new Event(parser.getDescription(), LocalDate.parse(parser.getDate(), FORMATTER));
                break;
            case TODO:
                newTask = new ToDo(parser.getDescription());
                break;
            default:
                break;

        }
    }

    public Task getNewTask() {
        return newTask;
    }

}
