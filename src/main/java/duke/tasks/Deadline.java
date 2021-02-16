package duke.tasks;

import duke.commands.CommandWord;
import duke.exceptions.InvalidInputFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class Deadline
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent a deadline in the task list
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor: creates a new Deadline
     * @param description Deadline description
     */
    public Deadline(String description, String date) throws InvalidInputFormatException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidInputFormatException(CommandWord.DEADLINE);
        }
    }

    /**
     * saveFormat: creates a formatted representation of a Deadline to be saved
     * @return representation of a Deadline to be saved
     */
    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + date;
    }

    /**
     * toString: creates a String representation of a Deadline
     * @return String representation of a Deadline
     */
    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}