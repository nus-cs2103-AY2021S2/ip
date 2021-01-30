package com.lirc572.ip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private LocalDateTime dueTime;

    public DeadlineTask(String name) {
        super(name);
    }

    public DeadlineTask(String name, String dueTime) {
        super(name);
        this.setDueTime(dueTime);
    }

    public void setDueTime(String dueTime) {
        this.dueTime = LocalDateTime.parse(dueTime, this.format);
    }

    public String getDueTime() {
        return this.dueTime.format(this.format);
    }

    @Override
    public String toSavedString() {
        return String.format(
                "D | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getDueTime()
        );
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (this.dueTime != null ? String.format(" (by: %s)", this.getDueTime()) : "");
    }
}
