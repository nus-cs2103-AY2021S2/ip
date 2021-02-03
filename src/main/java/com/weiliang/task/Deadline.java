package com.weiliang.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime timing;

    public Deadline(String task, String timing) {
        super(task);
        this.timing = LocalDateTime.parse(timing);
    }

    @Override
    public String toFormattedString() {
        return "D | " + (completed ? 1 : 0) + " | " + task + " | " + timing;
    }

    @Override
    public String toString() {
        return "[D][" + (completed ? "X" : " ") + "] " + task + " (by: "
                + timing.format(DateTimeFormatter.ofPattern("dd MMMM YYYY, hh:mma")) + ")";
    }

}
