package duke.task;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.storage.Storage;

/**
 * Class containing data and methods specific to a Event task.
 */
public class Event extends Task {
    private final DateTime dateTime;

    /**
     * Creates an Event object with a description and DateTime object.
     *
     * @param description description of the event
     * @param dateTime DateTime indicating the occurrence of the event
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = new DateTime(dateTime);
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
                + Storage.SPLITTER
                + (isDone() ? "1" : "0")
                + Storage.SPLITTER
                + getDescription()
                + Storage.SPLITTER
                + dateTime.getFormattedStorageString() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + dateTime.getDateTimeForDisplay() + "\n";
    }
}
