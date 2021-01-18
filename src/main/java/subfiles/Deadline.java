package main.java.subfiles;

public class Deadline extends Task {
    String date;

    public Deadline(String s, String t) {
        super(s);
        date = t;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + name + " (by: " + date + ")";
    }
}
