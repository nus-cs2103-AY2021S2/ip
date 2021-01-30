package duke.task;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.storage.Storage;

/**
 * Class containing data and methods specific to a Deadline task.
 */
public class Deadline extends Task {

    private final DateTime dateTime;

    /**
     * Creates a Deadline object with a description and a DateTime object.
     * @param description description of the deadline
     * @param dateTime DateTime indicating the deadline of the task
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = new DateTime(dateTime);
    }

    /**
     * Returns Deadline task created from arguments representing the user input.
     *
     * @param command user input
     * @return Deadline task
     * @throws DukeException if insufficient or invalid arguments are passed
     */
    public static Task createDeadline(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("There's not enough information about your Deadline order!");
        }
        String[] args = command[1].split(" /by ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your Deadline order isn't complete...");
        }
        return new Deadline(args[0], DateTime.convertStringToDate(args[1]));
    }

    public String getFormattedStorageString() {
        return "DEADLINE"
                + Storage.SPLITTER
                + (isDone() ? "1" : "0")
                + Storage.SPLITTER
                + getDescription()
                + Storage.SPLITTER
                + dateTime.getFormattedStorageString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + dateTime.getDateTimeForDisplay() + "\n";
    }
}
