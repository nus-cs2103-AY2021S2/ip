package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    Event(String name, LocalDate date) {
        super(name, "Event coming right up!");
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
