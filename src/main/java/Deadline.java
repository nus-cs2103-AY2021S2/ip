package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    Deadline(String name, LocalDate date) {
        super(name, "Make sure you meet this deadline!");
        this.date = date;
    }

    Deadline(String name, LocalDate date, Boolean status) {
        this(name, date);
        this.status = status;
    }

    @Override
    public String toFileString() {
        return "D," + (this.status ? "1" : "0") + "," + this.name + ","
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
