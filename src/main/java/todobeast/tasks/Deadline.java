package todobeast.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import todobeast.commands.TaskType;

/**
 * A Task that represents a deadline-type task. Deadlines have a time and date component.
 */
public class Deadline extends TimeBasedTask {

    public Deadline(String taskDescription, boolean isDone, LocalDate date, LocalTime time, String taskNotes) {
        super(TaskType.DEADLINE, taskDescription, isDone, date, time, taskNotes, "by ");
    }
}
