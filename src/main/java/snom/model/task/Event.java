package snom.model.task;

import snom.exceptions.SnomException;

/**
 * Stores Event {@code TaskWithDate}'s information
 */
public class Event extends TaskWithDate{
    public Event(String description, String dateTime) throws SnomException {
        super(description, dateTime);
    }

    @Override
    public String getSaveString(){
        return "E," + super.getSaveString();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + getDateTimeString() + ")";
    }
}
