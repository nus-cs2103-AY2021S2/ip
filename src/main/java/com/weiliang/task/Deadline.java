package com.weiliang.task;

import com.weiliang.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final LocalDateTime timing;

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
