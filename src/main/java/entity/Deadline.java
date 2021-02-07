package main.java.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task
 * A Deadline Task can be described with either name, keyword, deadlineString[, isDone]
 * or name, keyword, deadlineDate[, isDone]
 */
public class Deadline extends Task {

    protected String keyword;
    protected String deadline;
    protected LocalDate deadlineDate;

    /**
     * Creates a Deadline task whose default isDone status is false
     * and whose deadline is described by a string
     * @param name name of task
     * @param keyword preposition describing relationship to deadline specified
     * @param deadline string representation of deadline
     */
    public Deadline(String name, String keyword, String deadline) {
        super(name);
        this.deadline = deadline;
        this.keyword = keyword;
    }

    /**
     * Creates a Deadline task whose default isDone status is false
     * and whose deadline is described by a LocalDate data deadlineDate
     * @param name name of task
     * @param keyword preposition describing relationship to deadline specified
     * @param deadlineDate LocalDate representation of deadline
     */
    public Deadline(String name, String keyword, LocalDate deadlineDate) {
        super(name);
        this.deadlineDate = deadlineDate;
        this.keyword = keyword;
    }

    /**
     * Creates a Deadline task whose isDone status is given as input
     * and whose deadline is described by a string
     * @param name name of task
     * @param keyword preposition describing relationship to deadline specified
     * @param deadline string representation of deadline
     * @param isDone boolean isDone status
     */
    public Deadline(String name, String keyword, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
        this.keyword = keyword;
    }

    /**
     * Creates a Deadline task whose isDone status is given as input
     * and whose deadline is described by a LocalDate data deadlineDate
     * @param name name of task
     * @param keyword preposition describing relationship to deadline specified
     * @param deadlineDate LocalDate representation of deadline
     * @param isDone boolean isDone status
     */
    public Deadline(String name, String keyword, LocalDate deadlineDate, boolean isDone) {
        super(name, isDone);
        this.deadlineDate = deadlineDate;
        this.keyword = keyword;
    }

    /**
     * Override toString() method to be displayed in Ui
     * e.g. "[D][ ] cs2102 homework (by: 22 jan 2020)"
     * @return Display String representation of this Deadline task
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name + " (" + keyword + ": "
                + (this.deadlineDate == null ? this.deadline
                    : this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }

    /**
     * Override toFileString to be output and read from Storage
     * e.g. D|1|cs2102 homework|by|22 jan 2020
     * @return File String representation of this Deadline task
     */
    @Override
    public String toFileString() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.name + "|"
                + this.keyword + "|" + (this.deadlineDate == null ? this.deadline : this.deadlineDate);
    }


}
