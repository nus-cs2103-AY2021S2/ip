package com.lirc572.ip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private LocalDateTime eventTime;

    public EventTask(String name) {
        super(name);
    }

    public EventTask(String name, String eventTime) {
        super(name);
        this.setEventTime(eventTime);
    }

    public void setEventTime(String eventTime) {
        this.eventTime = LocalDateTime.parse(eventTime, this.format);
    }

    public String getEventTime() {
        return this.eventTime.format(this.format);
    }

    @Override
    public String toSavedString() {
        return String.format(
                "E | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getEventTime()
        );
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + (this.eventTime != null ? String.format(" (at: %s)", this.getEventTime()) : "");
    }
}
