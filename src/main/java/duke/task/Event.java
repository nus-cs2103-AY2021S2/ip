package duke.task;

import duke.DukeException;
import duke.storage.Storage;

import java.time.LocalDateTime;

/**
 * Class containing data and methods specific to a Event task.
 */
public class Event extends Task {
    public DateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = new DateTime(dateTime);
        isDone = false;
    }

    /**
     * Returns Event task created from arguments representing the user input.
     *
     * @param command user input
     * @return Event task
     * @throws DukeException if insufficient or invalid arguments are passed
     */
    public static Task createEvent(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("There's not enough information about your Event order!");
        }
        String[] args = command[1].split(" /at ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your Event order isn't complete...");
        }
        return new Event(args[0], DateTime.convertStringToDate(args[1]));
    }

    public String getFormattedStorageString() {
        return "EVENT"
                + Storage.splitter
                + (isDone ? "1" : "0")
                + Storage.splitter
                + description
                + Storage.splitter
                + dateTime.getFormattedStorageString() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + dateTime.getDateTimeForDisplay() + "\n";
    }
}
