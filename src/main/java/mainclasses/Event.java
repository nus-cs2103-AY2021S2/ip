package mainclasses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{



    public Event(String description, LocalDate eventDate, LocalTime eventTime) {
        super(description);
        this.date = eventDate;
        this.type = TaskEnum.E;
        this.time = eventTime;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("K:mm a");
        return this.description + " (at: " + this.date.format(dateFormatter) + " " + this.time.format(timeFormatter) + ")";
    }

}
