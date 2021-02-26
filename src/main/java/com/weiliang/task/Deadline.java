package com.weiliang.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.weiliang.DukeException;

/**
 * A deadline affiliated task.
 */
public class Deadline extends Task {

    private final LocalDateTime timing;

    /**
     * Instantiates a deadline with the given task description and timing.
     *
     * @param task The description of the task.
     * @param timing The time associated in String representation.
     * @throws DukeException If the timing provided is invalid.
     */
    public Deadline(String task, String timing) throws DukeException {
        super(task);
        try {
            this.timing = LocalDateTime.parse(timing);
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse date!");
        }
    }

    @Override
    public String toFormattedString() {
        return "D | " + (isComplete ? 1 : 0) + " | " + task + " | " + timing;
    }

    @Override
    public String toString() {
        return "[D][" + (isComplete ? "X" : " ") + "] " + task + " (by: "
                + timing.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mma")) + ")";
    }

}
