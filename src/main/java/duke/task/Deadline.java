package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;

public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates a Task with a description and a deadline date that it needs
     * to be done by.
     *
     * @param description Description of Deadline
     * @param by When the task needs to be completed
     * @throws EmptyArgumentException Some argument, either 'description' or 'by' is empty.
     * @throws BadDateArgumentException When 'by' is not well formatted
     */
    public Deadline(String description, String by) throws EmptyArgumentException, BadDateArgumentException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            this.deadline = LocalDate.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new BadDateArgumentException();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return "[D]" + super.toString() + " (Deadline: " + deadline.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String date = deadline.format(formatter);
        return "D," + super.toBaseFileString() + "," + date.length() + "," + date;
    }
}
