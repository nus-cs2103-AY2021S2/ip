package kobe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    private boolean done;
    private String taskName;
    private String type;
    private String condition;
    private LocalDate date;
    private boolean isConditionLocalDate;

    public Task(String taskName, String type, String condition) {
        this.done = false;
        this.taskName = taskName;
        this.type = type;
        this.condition = condition;
        this.isConditionLocalDate = false;
    }

    public Task(boolean done, String taskName, String type, String condition) {
        this.done = done;
        this.taskName = taskName;
        this.type = type;
        this.condition = condition;
        this.isConditionLocalDate = false;
    }

    public Task(boolean done, String taskName, String type, LocalDate date) {
        this.done = done;
        this.taskName = taskName;
        this.type = type;
        this.condition = "";
        this.date = date;
        this.isConditionLocalDate = true;

    }

    public void markAsDone() {
        this.done = true;
    }

    public static Task setAsDone(Task task) {
        task.done = true;
        return task;
    }

    public boolean getDoneStatus() {
        return this.done;
    }

    @Override
    public String toString() {
        String doneString = "[ ]";
        String typeString = "[ ]";
        String conditionString = "";

        if (done) {
            doneString = "[X]";
        }

        if (this.type.equals("todo")) {
            typeString = "[T]";
        } else if (this.type.equals("deadline")) {
            typeString = "[D]";
            if (!this.condition.equals("")) {
                conditionString = "(by: " + condition.substring(0) + ")";
            } else if (isConditionLocalDate) { //convert LocalDate to parsed date
                conditionString = "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } else {
            }

        } else if (this.type.equals("event")) {
            typeString = "[E]";
            if (!this.condition.equals("")) {
                conditionString = "(at: " + condition.substring(0) + ")";
            } else if (isConditionLocalDate) { //convert LocalDate to parsed date
                conditionString = "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } else {
            }

        } else {}

        return typeString + doneString + " " + taskName + " " + conditionString;
    }
}