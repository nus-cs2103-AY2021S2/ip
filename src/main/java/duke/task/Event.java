package duke.task;

import duke.exception.EmptyArgumentException;

public class Event extends Task {
    private String eventPeriod;

    public Event(String description, String eventPeriod) throws EmptyArgumentException {
        super(description);
        eventPeriod = eventPeriod.trim();
        if (eventPeriod.isEmpty()) {
            throw new EmptyArgumentException();
        }
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Event Time: " + eventPeriod + ")";
    }

    @Override
    public String toFileString() {
        return "E," + super.toBaseFileString() + "," + eventPeriod.length() + "," + eventPeriod;
    }
}
