package snom.model.task;

import snom.exceptions.SnomException;

/**
 * Stores Deadline {@code TaskWithDate}'s information
 */
public class Deadline extends TaskWithDate {
    public Deadline(String description, String dateTime) throws SnomException {
        super(description, dateTime);
    }

    @Override
    public String getSaveString(){
        return "D," + super.getSaveString();
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + getDateTimeString() + ")";
    }
}
