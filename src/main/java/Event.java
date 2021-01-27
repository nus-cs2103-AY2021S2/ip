package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event extends Task {
    @JsonProperty
    private String timeOfEvent;

    protected Event() {
        super();
    }

    public Event(String taskName, String timeOfEvent) {
        super(taskName);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", done ? "X" : " ", taskName, this.timeOfEvent);
    }
}
