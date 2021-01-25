package main.java.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    String keyword;
    String time;
    LocalDate timeDate;
    public Event(String name, String keyword, String time) {
        super(name);
        this.keyword = keyword;
        this.time = time;
    }

    public Event(String name, String keyword, String time, boolean isDone) {
        super(name, isDone);
        this.keyword = keyword;
        this.time = time;
    }

    public Event(String name, String keyword, LocalDate timeDate) {
        super(name);
        this.timeDate = timeDate;
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + this.keyword + ": " + (this.timeDate == null ? this.time : this.timeDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }

    @Override
    public String toFileString() {
        return "E|" + (this.isDone ? "1" : "0") + "|" + this.name + "|" + this.keyword + "|" + this.time;
    }

}
