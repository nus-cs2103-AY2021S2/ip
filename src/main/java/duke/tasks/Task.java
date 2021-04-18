package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a Task. Stores relevant information about the task like  basic description, the code letter that will
 * be displayed when the Task is printed, as well as the state of whether or not the Task is done.
 */
public abstract class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;
    protected String letterCode;
    protected LocalDate localDate = LocalDate.MAX;

    /**
     * Constructor.
     * @param description the description of the Task
     * @param letterCode the letter Code to indicate the type of Task ( for text-ui displaying )
     */

    public Task(String description, String letterCode) {
        this.description = description;
        this.letterCode = letterCode;
        this.isDone = false;
    }

    /**
     * Gives a symbol to indicate the state of whether the task is done.
     * tick means the task is done. X means the task is not yet done .
     * @return tick or X symbol
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * set the state of the task to be done.
     */

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the letter code for the task ( for example , 'T' for ToDo tasks );
     * @return letter code of the type of Task
     */

    public String getLetterCode() {
        return this.letterCode;
    }

    /**
     * Returns the task in the string representation used to save the Task on the hard disk.
     * (e.g letterCode | (1 or 0 depending on if task completed or not) | description of Task)
     * @return the string representing the task in saved format.
     */

    public String getSavedStringFormat() {
        String s = (isDone) ? "1" : "0";
        return letterCode + " | " + s + " | " + description;
    }

    /**
     * Return the string representation of Task ( normally for displauing purposes).
     * for example the format [letterCode][ "X "if done else ""][description]
     * @return string representation of Task
     */

    @Override
    public String toString() {
        String mark = getStatusIcon();
        return "[" + this.getLetterCode() + "]" + "[" + mark + "] " + description;
    }

    /**
     * Tasks are compared based on comparing the dates associated with the task.
     * A task is greater than another task if it has
     * a further date and vice versa.
     *
     * @param o the task to compare with this task
     * @return the comparator value of the two tasks, positive if t is greater than o, negative if lesser
     */
    @Override
    public int compareTo(Task o) {
        return this.localDate.compareTo(o.localDate);
    }

    /**
     * Compares two tasks to see if they are equal. A task is qual to another task
     * if the string representations are equal.
     * @param obj to be compared with
     * @return a boolean value, true if the two tasks are equal to each other and false if not equal.
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Task) {
            Task other = (Task) obj;
            return other.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
