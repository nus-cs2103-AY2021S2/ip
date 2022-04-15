package duke.parser.filestring;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Class to select appropriate FileStringParser.
 *
 * To adhere to SRP. Many different patterns...
 */
public class GetTaskFileStringParser {

    public TaskFileStringParser getFileStringParser(String fileString)
            throws DukeExceptionIllegalArgument {
        String[] tokens = fileString.split("\\t", 2);
        assert tokens.length > 1;
        switch (tokens[0]) {
        case "event":
            return new EventFileStringParser();
        case "todo":
            return new TodoFileStringParser();
        case "deadline":
            return new DeadlineFileStringParser();
        default:
            throw new DukeExceptionIllegalArgument("Invalid task type in file.");
        }
    }

    public TaskFileStringParser getFileStringParser(Task task)
            throws DukeExceptionIllegalArgument {
        if (task instanceof Event) {
            return new EventFileStringParser();
        } else if (task instanceof Todo) {
            return new TodoFileStringParser();
        } else if (task instanceof Deadline) {
            return new DeadlineFileStringParser();
        } else {
            throw new DukeExceptionIllegalArgument("Invalid task class.");
        }
    }
}
