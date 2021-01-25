package main.java.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    String keyword;
    String time;
    LocalDate timeDate;
    public Event(String name, String time, String keyword) {
        super(name);
        this.keyword = keyword;
        this.time = time;
    }

    public Event(String name, String time, LocalDate timeDate, String keyword) {
        super(name);
        this.timeDate = timeDate;
        this.keyword = keyword;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + this.keyword + ": " + (this.timeDate == null ? this.time : this.timeDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }


}
