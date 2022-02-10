package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

public class Event extends Task {
    private LocalDate date;
    private String preposition;

    /**
     * Constructor for event
     * @param taskName
     * @param date
     * @param preposition
     */
    public Event(String taskName, String date, String preposition) throws DukeException {
        super(taskName);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter a valid date format! YYYY-MM-DD");
        }
        this.preposition = preposition;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (%s: %s)",
                "E",
                super.isDone() ? "X" : " ",
                super.getName(),
                preposition,
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Getter method for date
     * @return
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s | %s | %s | %s",
                "E",
                super.isDone() ? "1" : 0,
                super.getName(),
                this.date,
                this.preposition);
    }
}
