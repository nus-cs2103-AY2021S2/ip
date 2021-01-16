package main.java.entity;

public class Deadline extends Task {

    String keyword;
    String deadline;
    public Deadline(String name, String deadline, String keyword) {
        super(name);
        this.deadline = deadline;
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + keyword + ": " + this.deadline + ")";
    }


}
