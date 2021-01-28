package com.tjtanjin.steve.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a child class of Task, containing additional
 * support for an endDate.
 */
public class Deadline extends Task {
    private final LocalDate endDate;

    /**
     * Constructor for Deadline class.
     * @param taskName name of task
     * @param status task completion status
     * @param taskDates array of dates (defaults to first element for deadline end date)
     */
    public Deadline(String taskName, String status, LocalDate[] taskDates) {
        super(taskName, status, "DEADLINE");
        this.endDate = taskDates[0];
    }

    @Override
    public LocalDate[] getDates() {
        LocalDate[] taskDates = new LocalDate[1];
        taskDates[0] = this.endDate;
        return taskDates;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
