package snom.model.task;

import snom.exceptions.SnomException;

public class Event extends TaskWithDate{
    public Event(String description, String dateTime) throws SnomException {
        super(description, dateTime);
    }

    @Override
    public String toSaveString(){
        return "E," + super.toSaveString();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + getDateTimeString() + ")";
    }
}
