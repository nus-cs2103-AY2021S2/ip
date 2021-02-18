package todobeast.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import todobeast.commands.TaskType;

public abstract class TimeBasedTask extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected String timeKeywordSpecifier;

    /**
     * Constructor for TimeBasedTask.
     * @param taskType the type of task
     * @param taskDescription the description for the task
     * @param isDone whether the task is done or not
     * @param date the date for the specified task
     * @param time the time for the specified task
     * @param taskNotes the notes to be added to this task
     * @param timeKeywordSpecifier the keyword specifier for the time
     */
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
