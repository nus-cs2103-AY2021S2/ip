package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String extractDateTime(String at) {
        String[] temp = at.split(" ");
        LocalDate date = LocalDate.parse(temp[0]);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + temp[1];
    }

    public String saveToFile() {
        return "E" + super.toString() + " | " + at;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + extractDateTime(at);
    }
}
