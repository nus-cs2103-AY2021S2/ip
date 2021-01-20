package main.java;

public class Deadline extends Task {

    protected String by;

    Deadline(String name, String by) {
        super(name, "Make sure you meet this deadline!");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
