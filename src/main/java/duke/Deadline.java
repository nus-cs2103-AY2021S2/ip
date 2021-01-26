package main.java.duke;

public class Deadline extends Task {
    protected static final String TAG = "[D]";

    public Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Deadline.TAG + super.toString();
    }
}
