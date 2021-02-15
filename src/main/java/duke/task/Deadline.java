package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private String preposition;

    /**
     * Constructor for deadline
     * @param taskName
     * @param date
     * @param preposition
     */
    public Deadline(String taskName, String date, String preposition) {
        super(taskName);
        this.date = LocalDate.parse(date);
        this.preposition = preposition;
    }

    /**
     * Getter method for date
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (%s: %s)",
                "D",
                super.isDone() ? "X" : " ",
                super.getName(),
                preposition,
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toSaveFormat() {
        String line = "D" + " | " + (super.isDone() ? "1" : "0") + " | " + super.getName();
        line += " | " + date + " | " + preposition;
        return line;
    }
}
