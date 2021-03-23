package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DatedTask extends Task {
    protected LocalDate date;

    /**
     * Checks if the provided date is of correct format then creates a new DatedTask with the specified
     * task description and due date.
     * @param task the task description
     * @param date due date
     * @throws TaskException if user provides incorrect date format for date.
     */
    public DatedTask(String task, String date) throws TaskException {
        super(task);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy]");
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new TaskException("Incorrect date format. " + e.getMessage());
        }
    }

    protected static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
