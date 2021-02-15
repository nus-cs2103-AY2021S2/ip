package todobeast.tasks;

import todobeast.commands.TaskType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that represents a deadline-type task. Deadlines have a time and date component.
 */
public class Deadline extends TimeBasedTask {

    public Deadline(String taskDescription, boolean isDone, LocalDate date, LocalTime time, String taskNotes) {
        super(TaskType.DEADLINE, taskDescription, isDone, date, time, taskNotes, "by ");
    }
}
