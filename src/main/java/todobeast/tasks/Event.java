package todobeast.tasks;

import todobeast.commands.TaskType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that represents a event-type task. Events have a time and date component.
 */
public class Event extends TimeBasedTask {

    public Event(String taskDescription, boolean isDone, LocalDate date, LocalTime time, String taskNotes) {
        super(TaskType.EVENT, taskDescription, isDone, date, time, taskNotes, "at ");
    }
}
