package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String name, String at) {
        super(name);
        this.at = LocalDate.parse(at);
    }

    public String getDisplayAt() {
        return this.at.format(DateTimeFormatter.ofPattern(Event.DATE_DISPLAY_FORMAT));
    }

    public String getSaveAt() {
        return this.at.format(DateTimeFormatter.ofPattern(Event.DATE_SAVE_FORMAT));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDisplayAt() + ")";
    }
}
