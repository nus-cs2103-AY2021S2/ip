package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final String preposition;
    private final LocalDate date;

    public Event(String description, String preposition, LocalDate date) {
        super(description);
        this.preposition = preposition;
        this.date =date;
    }

    @Override
    String toFileString() {
        return String.format("event %s | %s %s", description,
                preposition, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String toString(){
        String dateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E][%s] %s (%s %s)", getStatusIcon(), description, preposition, dateStr);
    }
}
