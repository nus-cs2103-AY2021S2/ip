package duke.task;

import duke.DukeException;
import duke.storage.Storage;

import java.time.LocalDateTime;

/**
 * Class containing data and methods specific to a Deadline task.
 */
public class Deadline extends Task {

    public DateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = new DateTime(dateTime);
        isDone = false;
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
                + Storage.splitter
                + (isDone ? "1" : "0")
                + Storage.splitter
                + description
                + Storage.splitter
                + dateTime.getFormattedStorageString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + dateTime.getDateTimeForDisplay() + "\n";
    }
}
