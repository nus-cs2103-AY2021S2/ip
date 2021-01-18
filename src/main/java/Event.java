package main.java;

public class Events extends Task {
    protected String eventAt;
    public Events(String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

}
