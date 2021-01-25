package main.java.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    String keyword;
    String deadline;
    LocalDate deadlineDate;
    public Deadline(String name, String keyword, String deadline) {
        super(name);
        this.deadline = deadline;
        this.keyword = keyword;
    }

    public Deadline(String name, String keyword, LocalDate deadlineDate) {
        super(name);
        this.deadlineDate = deadlineDate;
        this.keyword = keyword;
    }

    public Deadline(String name, String keyword, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
        this.keyword = keyword;
    }



    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + keyword + ": " + (this.deadlineDate == null ? this.deadline : this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }

    @Override
    public String toFileString() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.name + "|" + this.keyword + "|" + this.deadline;
    }


}
