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

/**
 * Handles all task related logic for duke and returns the newly created task to
 * be added.
 */
public class DukeTaskHandler {
    private Task newTask;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * constructs the task handler by taking in a parser that has parsed the input
     * and a task type to determine what type of task is created
     * @param parser   the parser that has been given user input
     * @param taskType the task that the handler should create
     * @throws TaskException invalid arguments or missing arguments
     */
    public DukeTaskHandler(Parser parser, Command taskType) throws TaskException {
        if (parser.hasDescriptionError()) {
            Parser.exception("The is no description, please enter description.");
        } else if (parser.hasDateError()) {
            Parser.exception("Date is either invalid or not entered, please rectify.");
        }

        TaskType typeOfTask = TaskType.valueOf(taskType.toString());
        switch (typeOfTask) {
        case DEADLINE:
            newTask = new Deadline(parser.getDescription(), LocalDate.parse(parser.getDate(), formatter));
            break;
        case EVENT:
            newTask = new Event(parser.getDescription(), LocalDate.parse(parser.getDate(), formatter));
            break;
        case TODO:
            newTask = new ToDo(parser.getDescription());
            break;
        default:
            break;

        }
    }

    /**
     * returns the task created by the task handler
     * @return new task created by task handler
     */
    public Task getNewTask() {
        return newTask;
    }

}
