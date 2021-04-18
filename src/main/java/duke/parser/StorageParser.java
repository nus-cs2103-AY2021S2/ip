package duke.parser;

import duke.exceptions.DukeCommandParseException;
import duke.exceptions.DukeDateParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * A class containing static methods used to parse strings obtained from the local txt file into tasks.
 * vice versa, it also converts tasks to a string to save into the txt file.
 */

public class StorageParser {
    private static final String STORAGE_PARSE_ERROR_MESSAGE = "Please delete contents "
            + "of the file data.duke and try again.";

    /**
     * Static method for parsing a special string representation of a Task which is used
     * to store the Task in hard disk. Returns the corresponding Task.
     *
     * @param input String representation of Task as it is stored in the hard disk.
     * @return the corresponding Task.
     */
    public static Task parseTaskFromStorageFormat(String input)
            throws DukeCommandParseException, DukeDateParseException {
        Task parsedTask;
        String[] fields = input.split(" \\| ");
        String commandCode = fields[0];
        switch (commandCode) {
        case ("T"):
            parsedTask = new ToDo(fields[2]);
            break;
        case ("D"):
            parsedTask = new Deadline(fields[2], fields[3]);
            break;
        case ("E"):
            parsedTask = new Event(fields[2], fields[3]);
            break;
        default:
            throw new DukeCommandParseException(STORAGE_PARSE_ERROR_MESSAGE);
        }
        boolean isDone = (Integer.parseInt(fields[1]) == 1);
        if (isDone) {
            parsedTask.markAsDone();
        }
        return parsedTask;
    }

    /**
     * Returns the string to save the task in.
     * @param task Task to be saved
     * @return the string to save the task in.
     */

    public static String convertTaskToStorageFormat(Task task) {
        return task.getSavedStringFormat();
    }

}
