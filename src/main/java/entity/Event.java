package main.java.entity;

public class Event extends Task {

    String keyword;
    String time;
    public Event(String name, String time, String keyword) {
        super(name);
        this.keyword = keyword;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + this.keyword + ": " + this.time + ")";
    }


}
