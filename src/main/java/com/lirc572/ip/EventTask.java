package com.lirc572.ip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task (task with a time).
 */
public class EventTask extends Task {

    /**
     * Format of datetime for parsing and printing.
     */
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * the time of the event.
     */
    private LocalDateTime eventTime;

    /**
     * Constructs a new EventTask with the specified name.
     *
     * @param name The name of the event.
     */
    public EventTask(String name) {
        super(name);
    }

    /**
     * Constructs a new EventTask with the specified name and time.
     *
     * @param name The name of the event.
     * @param eventTime The time of the event.
     */
    public EventTask(String name, String eventTime) {
        super(name);
        if (eventTime != null) {
            this.setEventTime(eventTime);
        }
    }

    /**
     * Sets the time of the EventTask.
     *
     * @param eventTime The new time of the event.
     */
    public void setEventTime(String eventTime) {
        this.eventTime = LocalDateTime.parse(eventTime, this.format);
    }

    /**
     * Returns the time of the EventTask.
     * @return The time of the EventTask.
     */
    public String getEventTime() {
        return this.eventTime == null ? null : this.eventTime.format(this.format);
    }

    /**
     * Returns the string representation of the DeadlineTask for storage.
     *
     * @return The string representation of the DeadlineTask for storage.
     */
    @Override
    public String toSavedString() {
        return String.format(
                "E | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getEventTime() == null ? "null" : this.getEventTime()
        );
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + (this.eventTime != null ? String.format(" (at: %s)", this.getEventTime()) : "");
    }
}
