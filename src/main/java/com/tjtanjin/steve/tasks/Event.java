package com.tjtanjin.steve.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a child class of Task, containing additional
 * support for an endDate.
 */
public class Event extends Task {
    private final LocalDate START_DATE;
    private final LocalDate END_DATE;

    /**
     * Constructor for Event class.
     *
     * @param taskName name of task
     * @param status task completion status
     * @param taskDates array of dates (first element is start date, second element is end date)
     */
    public Event(String taskName, String status, LocalDate[] taskDates) {
        super(taskName, status, "EVENT");
        this.START_DATE = taskDates[0];
        this.END_DATE = taskDates[1];
    }

    /**
     * Gets array of due dates, 2 elements for event class.
     *
     * @return array with 2 elements representing start and end date
     */
    @Override
    public LocalDate[] getDates() {
        LocalDate[] taskDates = new LocalDate[2];
        taskDates[0] = this.START_DATE;
        taskDates[1] = this.END_DATE;
        return taskDates;
    }

    /**
     * Gets the string describing event.
     *
     * @return String describing event
     */
    @Override
    public String toString() {
        return super.toString()
                + " (from: " + this.START_DATE.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " to: " + this.END_DATE.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
