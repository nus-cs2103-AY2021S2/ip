package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.EnumTask;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class that represents a TaskParser.
 */
public class TaskParser implements Parser {
    /**
     * Returns a Task constructed from the given inputs.
     * @param taskType The Enum taskType.
     * @param descriptions The relevant descriptions to construct a task.
     * @return A Task constructed from the given inputs.
     * @throws DukeException If error occurs during the process.
     */
    public static Task parseTask(EnumTask taskType, String ... descriptions) throws DukeException {
        String name = descriptions[0];
        switch (taskType) {
        case TODO:
            return new Todo(name);
        case DEADLINE:
            String deadline = descriptions[1].strip();
            LocalDateTime cutOffTime = DateTimeParser.parseDateTime(deadline);
            return new Deadline(name, cutOffTime);
        case EVENT:
            String eventTime = descriptions[1].strip();
            LocalDateTime startingTime = DateTimeParser.parseDateTime(eventTime);
            return new Event(name, startingTime);
        default:
            throw new DukeException("I do not know this task");
        }
    }
}
