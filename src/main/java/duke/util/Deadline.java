package duke.util;

import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents an deadline task.
 */
public class Deadline extends Task {

    private static final String TYPE = "D";

    /**
     * Factory method for creating deadline task.
     * @param input Description of the task and its due date.
     *     Due date should be indicated after "/by".
     * @return A deadline task.
     * @throws DukeInputException If due date is missing.
     */
    public static Deadline createDeadline(String input) throws DukeInputException {
        String[] details = input.split(" /by ");

        if (!input.contains("/by") || details.length == 1) {
            throw new DukeInputException("Due date is missing!");
        }

        LocalDate date;
        
        try {
            date = LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new DukeInputException("Wrong date format! Please use YYYY-MM-DD");
        }

        return new Deadline(details[0], date);
    }

    private Deadline(String description, LocalDate date) {
        super(description, date);
    }

    private Deadline(boolean isDone, String description, LocalDate date) {
        super(description, date);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                TYPE,
                super.toString(),
                date.format(DateTimeFormatter.ofPattern("d MMM")));
    }

    /**
     * Export data into a standardised format.
     */
    @Override
    protected List<String> exportData() {
        return List.of(TYPE,
                isDone ? "1" : "0",
                description,
                date.toString());
    }

    /**
     * Import data from standardised format.
     */
    protected static Deadline importData(String[] args) {
        boolean isDone = args[1].equals("1");
        return new Deadline(isDone, args[2], LocalDate.parse(args[3]));
    }
}
