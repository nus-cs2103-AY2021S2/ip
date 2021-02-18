package com.weiliang.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.weiliang.DukeException;

/**
 * An event task with a given date.
 */
public class Event extends Task {

    private final LocalDateTime timing;

    /**
     * Instantiates an event with the given task description and timing.
     *
     * @param task The description of the task.
     * @param timing The time associated in String representation.
     * @throws DukeException If the timing provided is invalid.
     */
    public Event(String task, String timing) throws DukeException {
        super(task);
        try {
            this.timing = LocalDateTime.parse(timing);
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse date!");
        }
    }

    @Override
    public String toFormattedString() {
        return "E | " + (isComplete ? 1 : 0) + " | " + task + " | " + timing;
    }

    @Override
    public String toString() {
        return "[E][" + (isComplete ? "X" : " ") + "] " + task + " (at: "
                + timing.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mma")) + ")";
    }

}
