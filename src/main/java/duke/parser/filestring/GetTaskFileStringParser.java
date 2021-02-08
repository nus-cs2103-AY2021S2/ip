package duke.parser.filestring;

import duke.exceptions.DukeExceptionIllegalArgument;

/**
 * Class to select appropriate FileStringParser.
 *
 * To adhere to SRP. Many different patterns...
 */
public class GetTaskFileStringParser {

    public static Class<? extends TaskFileStringParser> getFileStringParser(String fileString)
            throws DukeExceptionIllegalArgument {
        String[] tokens = fileString.split("\t", 1);
        switch (tokens[0]) {
        case "event":
            return EventFileStringParser.class;
        case "todo":
            return TodoFileStringParser.class;
        case "deadline":
            return DeadlineFileStringParser.class;
        default:
            throw new DukeExceptionIllegalArgument("Invalid task type in file.")
        }
    }
}
