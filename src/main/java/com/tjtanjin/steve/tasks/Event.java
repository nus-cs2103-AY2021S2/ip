package com.tjtanjin.steve.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a child class of Task, containing additional
 * support for an endDate.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor for Event class.
     * @param taskName name of task
     * @param status task completion status
     * @param taskDates array of dates (first element is start date, second element is end date)
     */
    public Event(String taskName, String status, LocalDate[] taskDates) {
        super(taskName, status, "EVENT");
        this.startDate = taskDates[0];
        this.endDate = taskDates[1];
    }

    @Override
    public LocalDate[] getDates() {
        LocalDate[] taskDates = new LocalDate[2];
        taskDates[0] = this.startDate;
        taskDates[1] = this.endDate;
        return taskDates;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
