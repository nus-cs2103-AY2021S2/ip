package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /**
     * The date/time that the Deadline should be done by
     */
    private LocalDateTime doneBy;

    /**
     * Initiates a deadline, time is set to 0000 if no time is provided.
     *
     * @param s      The task.
     * @param doneBy The date and time to finish the task by.
     */
    public Deadline(String s, String doneBy) {
        super(s);
        String[] split = doneBy.split("\\s+");
        if (split.length == 1) {
            this.doneBy = LocalDate.parse(doneBy).atTime(0, 0);
        } else if (split.length == 2) {
            this.doneBy = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
        } else {
            throw new DateTimeParseException("", "", 1);
        }
        assert(this.doneBy != null);

    }

    /**
     * Returns a String form of the current Deadline to be saved onto the hard disk.
     *
     * @return the Deadline as a String to be saved.
     */
    public String saveToData() {
        if (this.isDone) {
            return ("D | 1 | " + todo + " | " + doneBy.toString().replace("T", " ")
                    + " | " + saveTags());
        } else {
            return ("D | 0 | " + todo + " | " + doneBy.toString().replace("T", " ")
                    + " | " + saveTags());
        }
    }

    @Override
    public String toString() {
        if (!this.isDone) {
            return ("[D][ ] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")"
                    + " " + showTags());
        } else {
            return ("[D][X] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")"
                    + " " + showTags());
        }
    }
}
