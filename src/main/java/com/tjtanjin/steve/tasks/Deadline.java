package com.tjtanjin.steve.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a child class of Task, containing additional
 * support for an endDate.
 */
public class Deadline extends Task {
    private final LocalDate END_DATE;

    /**
     * Constructor for Deadline class.
     *
     * @param taskName name of task
     * @param status task completion status
     * @param taskDates array of dates (defaults to first element for deadline end date)
     */
    public Deadline(String taskName, String status, LocalDate[] taskDates) {
        super(taskName, status, "DEADLINE");
        this.END_DATE = taskDates[0];
    }

    /**
     * Gets array of due dates, only 1 element for Deadline class.
     *
     * @return array of due dates with 1 element
     */
    @Override
    public LocalDate[] getDates() {
        LocalDate[] taskDates = new LocalDate[1];
        taskDates[0] = this.END_DATE;
        return taskDates;
    }

    /**
     * Marks deadline task as completed.
     */
    @Override
    public Task markCompleted() {
        return new Deadline(getTaskName(), "complete", getDates());
    }

    /**
     * Gets the string describing deadline.
     *
     * @return String describing deadline
     */
    @Override
    public String toString() {
        return super.toString()
                + " (by: " + this.END_DATE.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
