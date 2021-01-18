package main.java.subfiles;

public class Event extends Task {
    String date;

    public Event(String s, String t) {
        super(s);
        date = t;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + name + " (at: " + date + ")";
    }
}
