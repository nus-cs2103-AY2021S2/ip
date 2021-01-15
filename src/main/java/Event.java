package main.java;

public class Event extends Task {
    protected static final String TAG = "[E]";

    Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Event.TAG + super.toString();
    }
}
