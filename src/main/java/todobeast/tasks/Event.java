package todobeast.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import todobeast.commands.TaskType;

/**
 * A Task that represents a event-type task. Events have a time and date component.
 */
public class Event extends TimeBasedTask {

    public Event(String taskDescription, boolean isDone, LocalDate date, LocalTime time, String taskNotes) {
        super(TaskType.EVENT, taskDescription, isDone, date, time, taskNotes, "at ");
    }
}
