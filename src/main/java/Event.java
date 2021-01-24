package main.java;

public class Event extends Task {
    protected String at;

    Event(String name, String at) {
        super(name, "Event coming right up!");
        this.at = at;
    }

    Event(String name, String at, Boolean status) {
        this(name, at);
        this.status = status;
    }

    @Override
    public String toFileString() {
        return "E," + (this.status ? "1" : "0") + "," + this.name + "," + this.at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
