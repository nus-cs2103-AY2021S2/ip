package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents an deadline task.
 */
public class Deadline extends Task {

    private static final String TYPE = "D";

    private Deadline(String description, LocalDate date) {
        super(description, date);
    }

    private Deadline(String description, LocalDate date, boolean isDone) {
        super(description, date);
        this.isDone = isDone;
    }

    /**
     * Factory method for creating deadline task.
     *
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

        assert date != null : "Date was not parsed.";
        return new Deadline(details[0], date);
    }

    /**
     * Returns String in the form "[Type] task".
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                TYPE,
                super.toString(),
                date.format(DateTimeFormatter.ofPattern("d MMM")));
    }

    /**
     * Export data into a standardised format.
     *
     * @return List of Deadline details.
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
     *
     * @param args Deadline details.
     * @return Deadline object.
     */
    protected static Deadline importData(String[] args) {
        assert args[1].equals("1") || args[1].equals("0") : "Parser.checkImportFormat() missed an invalid input";

        boolean isDone = args[1].equals("1");
        return new Deadline(args[2], LocalDate.parse(args[3]), isDone);
    }

    /**
     * Returns a new Deadline task marked as done.
     *
     * @return A Deadline object.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(description, date, true);
    }
}
