package duke;

import java.time.LocalDate;

/**
 * The Deadline class has methods for a Deadline object
 * Inherits from the Task.
 */

public class Deadline extends Task implements Comparable<Deadline>{

    private LocalDate deadline;

    /**
     * Constructor for new Deadline
     *
     * @param description of the new Deadline
     * @param deadline from new Deadline
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDate getLocalDate(){
        return this.deadline;
    }

    /**
     * representates a Deadline object as a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }

    @Override
    public int compareTo(Deadline o) {
        if(this.deadline.isBefore(o.deadline)){
            return -1;
        } else if(this.deadline.isAfter(o.deadline)){
            return 1;
        } else {
            return 0;
        }
    }
}
