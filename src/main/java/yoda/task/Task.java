package yoda.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String formatDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    public void setDateTime(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, format);
    }

    public String checkStatus() {
        return (isDone ? "\u2713" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + checkStatus() + "] " + description + " ---> ";
    }
}
