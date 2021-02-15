package todobeast.tasks;

import todobeast.commands.TaskType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class TimeBasedTask extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected String timeKeywordSpecifier;

    public TimeBasedTask(TaskType taskType, String taskDescription, boolean isDone, LocalDate date, LocalTime time,
                         String taskNotes, String timeKeywordSpecifier) {
        super(taskType, taskDescription, isDone, taskNotes);
        this.date = date;
        this.time = time;
        this.timeKeywordSpecifier = timeKeywordSpecifier;
    }

    @Override
    public String formatForStorage(String delimiter) {
        String doneIndicator = isDone ? "1" : "0";
        String taskNotesForStorage = hasTaskNotes() ? delimiter + taskDescription : "";
        return taskType.toString() + delimiter
                + doneIndicator + delimiter
                + taskDescription + delimiter
                + date.toString() + delimiter + time.toString()
                + taskNotesForStorage;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "✔" : " ";
        String taskNotesForDisplay = hasTaskNotes() ? Task.TASK_DELIMITER + taskNotes : "";
        String formattedDate = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return taskType.toString() + Task.TASK_DELIMITER
                + doneIndicator + Task.TASK_DELIMITER
                + taskDescription + Task.TASK_DELIMITER
                + timeKeywordSpecifier + formattedDate + ", "
                + time.toString()
                + taskNotesForDisplay;
    }

}
