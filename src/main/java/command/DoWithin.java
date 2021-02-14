package command;

import java.util.Date;

import classes.Task;

public class DoWithin extends Task {
    protected Date from;
    protected Date to;
    /**
     * Construct a Task object with a given description and isDone set to false.
     *
     * @param description The description of this task.
     */
    public DoWithin(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return this.from;
    }

    public Date getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[DW]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
