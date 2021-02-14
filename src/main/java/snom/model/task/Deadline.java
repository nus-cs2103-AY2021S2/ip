package snom.model.task;

import snom.exceptions.SnomException;

public class Deadline extends TaskWithDate {
    public Deadline(String description, String dateTime) throws SnomException {
        super(description, dateTime);
    }

    @Override
    public String toSaveString(){
        return "D," + super.toSaveString();
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + getDateTimeString() + ")";
    }
}
