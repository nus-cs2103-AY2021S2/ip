package main.java.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task
 * A Event Task can be described with either name, keyword, time[, isDone]
 * or name, keyword, timeDate[, isDone]
 */
public class Event extends Task {

    protected String keyword;
    protected String time;
    protected LocalDate timeDate;

    /**
     * Creates a Event task whose default isDone status is false
     * and whose time is described by a string
     * @param name name of task
     * @param keyword keyword describing time
     * @param time string representation of time for event task
     */
    public Event(String name, String keyword, String time) {
        super(name);
        this.keyword = keyword;
        this.time = time;
    }

    /**
     * Creates a Event task whose isDone status is given as input
     * and whose time is described by a string
     * @param name name of task
     * @param keyword keyword describing time
     * @param time string representation of time for event task
     * @param isDone boolean status
     */
    public Event(String name, String keyword, String time, boolean isDone) {
        super(name, isDone);
        this.keyword = keyword;
        this.time = time;
    }

    /**
     * Creates a Event task whose default isDone status is false
     * and whose time is described by a LocalDate data timeDate
     * @param name name of task
     * @param keyword keyword describing time
     * @param timeDate LocalDate time for event task
     */
    public Event(String name, String keyword, LocalDate timeDate) {
        super(name);
        this.timeDate = timeDate;
        this.keyword = keyword;
    }

    /**
     * Creates a Event task whose isDone status is given as input
     * and whose time is described by a LocalDate data timeDate
     * @param name name of task
     * @param keyword keyword describing time
     * @param timeDate LocalDate time for event task
     * @param isDone boolean status
     */
    public Event(String name, String keyword, LocalDate timeDate, boolean isDone) {
        super(name, isDone);
        this.timeDate = timeDate;
        this.keyword = keyword;
    }

    /**
     * Override toString() method to be displayed in Ui
     * e.g. "[E][ ] alice's birthday (on: 22 jan 2020)"
     * @return Display String representation of this Event task
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + this.keyword + ": "
                + (this.timeDate == null ? this.time
                    : this.timeDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }

    /**
     * Override toFileString to be output and read from Storage
     * e.g. E|1|alice's birthday|on|22 jan 2020
     * @return File String representation of this Event task
     */
    @Override
    public String toFileString() {
        return "E|" + (this.isDone ? "1" : "0") + "|" + this.name + "|"
                + this.keyword + "|" + (this.timeDate == null ? this.time : this.timeDate);
    }

}
