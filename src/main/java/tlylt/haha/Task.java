package tlylt.haha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final String description;
    protected boolean isDone = false;
    protected final String type;
    protected LocalDate date;
    protected LocalTime time;

    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.isDone = isDone;
        this.description = description;
    }

    public Task(String type, boolean isDone, String description, LocalDate date, LocalTime time) {
        this(type, isDone, description);
        this.date = date;
        this.time = time;
    }

    public String getStatusIcon() {
        //return [X] or [ ] symbols
        return (isDone ? "[X]" : "[ ]");
    }

    public String getTypeIcon() {
        return "[" + type + "]";
    }

    public String modifiedDescription(String id) {
        String formatDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatTime = time.format(DateTimeFormatter.ofPattern("hh.mm a"));
        String formatDateTime = "(" + id + ": " + formatDate + " " + formatTime + ")";
        return description.substring(0, description.indexOf('/')) + formatDateTime;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String fileStorageFormat() {
        return type + "|" + getIsDone() + "|" + description;
    }

}
