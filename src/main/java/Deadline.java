package main.java;

public class Deadline extends Task {
    protected static final String TAG = "[D]";

    Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Deadline.TAG + super.toString();
    }
}
