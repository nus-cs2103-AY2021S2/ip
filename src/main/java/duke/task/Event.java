package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    private String preposition;

    /**
     * Constructor for event
     * @param taskName
     * @param date
     * @param preposition
     */
    public Event(String taskName, String date, String preposition) {
        super(taskName);
        this.date = LocalDate.parse(date);
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
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toSaveFormat() {
        String line = "E" + " | " + (super.isDone() ? "1" : "0") + " | " + super.getName();
        line += " | " + date + " | " + preposition;
        return line;
    }
}
