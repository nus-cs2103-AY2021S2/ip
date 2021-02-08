package duke.parser.filestring;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.Deadline;

/**
 * Parser class to read and write Deadline to file.
 */
public class DeadlineFileStringParser extends TaskFileStringParser {

    /**
     * Generates file string for Deadline.
     *
     * @param deadline Deadline
     * @return string representation of deadline to be written to file
     */
    public String toFileString(Deadline deadline) {
        String description = deadline.getDescription();
        String datetime = deadline.getDatetimeString();
        String done = deadline.getDone() ? "1" : "0";
        return String.format("deadline\t%s\t%s\t%s", description, datetime, done);
    }

    /**
     * Reads file string for Deadline.
     *
     * @param deadlineFileString string representation of Deadline
     * @return Deadline
     * @throws DukeExceptionIllegalArgument When Deadline parsing fails
     */
    public static Deadline fromFileString(String deadlineFileString) throws DukeExceptionIllegalArgument {
        String[] data = deadlineFileString.split("\t");
        assert data.length == 4;
        assert data[0].equals("deadline");
        String description = data[1];
        String datetime = data[2];
        boolean done = data[3].equals("1");
        return new Deadline(description, datetime, done);
    }
}
