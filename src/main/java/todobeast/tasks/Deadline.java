package todobeast.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline(String taskDescription, LocalDate date, LocalTime time) {
        super(taskDescription);
        this.date = date;
        this.time = time;
    }

    public Deadline(String taskDescription, boolean isDone, LocalDate date, LocalTime time) {
        super(taskDescription, isDone);
        this.date = date;
        this.time = time;
    }

    public String formatForStorage(String delimiter) {
        return "DEADLINE" + delimiter + (isDone ? "1" : "0") + delimiter + taskDescription + delimiter + date.toString() + delimiter + time.toString();
    }

    @Override
    public String toString() {
        return "DEADLINE" + Task.TASK_DELIMITER + (isDone ? "done" : " ") + Task.TASK_DELIMITER + taskDescription + Task.TASK_DELIMITER + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ", " + time;
    }
}
