package duke.parser.filestring;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.Task;

/**
 * Abstract parent for individual filestring parsers.
 */
public abstract class TaskFileStringParser {

    public abstract String toFileString(Task task);

    public abstract Task fromFileString(String deadlineFileString) throws DukeExceptionIllegalArgument;
}
