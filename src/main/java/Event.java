package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    Event(String name, LocalDate date) {
        super(name, "Event coming right up!");
        this.date = date;
    }

    Event(String name, String at, Boolean status) {
        this(name, at);
        this.status = status;
    }

    @Override
    public String toFileString() {
        return "E," + (this.status ? "1" : "0") + "," + this.name + "," + this.at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
