package main.java.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    String keyword;
    String deadline;
    LocalDate deadlineDate;
    public Deadline(String name, String deadline, String keyword) {
        super(name);
        this.deadline = deadline;
        this.keyword = keyword;
    }

    public Deadline(String name, String deadline, LocalDate deadlineDate, String keyword) {
        super(name);
        this.deadline = deadline;
        this.deadlineDate = deadlineDate;
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + keyword + ": " + (this.deadlineDate == null ? this.deadline : this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }


}
