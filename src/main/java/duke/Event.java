package main.java.duke;

public class Event extends Task {
    protected static final String TAG = "[E]";

    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Event.TAG + super.toString();
    }
}
