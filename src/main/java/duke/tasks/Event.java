package duke.tasks;

import duke.commands.CommandWord;
import duke.exceptions.InvalidInputFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class Event
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an event in the task list
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructor: creates a new Event
     * @param description Event description
     */
    public Event(String description, String date) throws InvalidInputFormatException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidInputFormatException(CommandWord.EVENT);
        }
    }

    /**
     * saveFormat: creates a formatted representation of an Event to be saved
     * @return representation of an Event to be saved
     */
    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + date;
    }

    /**
     * toString: creates a String representation of an Event
     * @return String representation of an Event
     */
    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
