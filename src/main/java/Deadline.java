package main.java;

public class Deadline extends Task {

    protected String by;

    Deadline(String name, String by) {
        super(name, "Make sure you meet this deadline!");
        this.by = by;
    }

    Deadline(String name, String by, Boolean status) {
        this(name, by);
        this.status = status;
    }

    @Override
    public String toFileString() {
        return "D," + (this.status ? "1" : "0") + "," + this.name + "," + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
