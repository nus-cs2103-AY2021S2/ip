package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    /**
     * The time at which the Event happens
     */
    private LocalDateTime time;

    /**
     * Initiates a Event, time is set to 0000 if none is provided.
     *
     * @param s    The task.
     * @param time The time when the task occurs.
     * @throws DateTimeParseException
     */
    public Event(String s, String time) throws DateTimeParseException {
        super(s);
        String[] split = time.split("\\s+");
        if (split.length == 1) {
            this.time = LocalDate.parse(time).atTime(0, 0);
        } else if (split.length == 2) {
            this.time = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
        } else {
            throw new DateTimeParseException("", "", 1);
        }
        assert(this.time != null);
    }

    /**
     * Returns a String form of the current Event to be saved onto the hard disk.
     *
     * @return the Event as a String to be saved.
     */
    public String saveToData() {
        if (this.isDone) {
            return ("E | 1 | " + todo + " | " + time.toString().replace("T", " ")
                    + " | " + saveTags());
        } else {
            return ("E | 0 | " + todo + " | " + time.toString().replace("T", " ")
                    + " | " + saveTags());
        }
    }

    @Override
    public String toString() {
        if (!this.isDone) {
            return ("[E][ ] " + this.todo + " (at:" + dateFormat(this.time) + ")"
                    + " " + showTags());
        } else {
            return ("[E][X] " + this.todo + " (at:" + dateFormat(this.time) + ")"
                    + " " + showTags());
        }
    }
}
