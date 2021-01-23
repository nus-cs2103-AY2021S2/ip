package surrealchat.task;

import java.time.LocalDate;

public class EventTask extends Task {
    private LocalDate event;
    public EventTask(String taskDescription, LocalDate event, boolean isDone) {
        super(taskDescription, "E", isDone);
        this.event = event;
    }

    @Override
    public String saveTask() {
        return String.format("%s /at %s", super.saveTask(), this.event);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.event);
    }
}
