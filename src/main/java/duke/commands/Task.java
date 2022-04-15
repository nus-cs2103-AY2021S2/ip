package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean isDone;
    private final String taskDetail;

    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
        this.isDone = false;
    }

    public String getTaskDetail() {
        return this.taskDetail;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    protected static LocalDate dateStringToDate(String inputDate) {
        String customInputDate = inputDate.replaceAll("-", " ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate date = LocalDate.parse(customInputDate, format);
        return date;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] | " + this.getTaskDetail();
    }

}
