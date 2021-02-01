package com.jetbrains;

public class Event extends Task {
    String eventDate;

    Event(String input) {
        String[] inputs = input.trim().split("/at ");
        this.task = inputs[0].substring(6);
        this.isDone = false;
        this.eventDate = inputs[1];
    }

    @Override
    public String toString() {
        return String.format("EVNT%s (at: %s)" ,
                super.toString(), eventDate);
    }
}
