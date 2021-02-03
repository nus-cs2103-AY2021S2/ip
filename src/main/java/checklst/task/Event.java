package checklst.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import checklst.exception.ChecklstException;

/**
 * The Event class represents a Task which has a specific DateTime.
 */
public class Event extends Task {

    protected final LocalDate eventDate;

    protected Event(String name, LocalDate eventDate) {
        super(name);
        this.eventDate = eventDate;
    }

    protected Event(String name, boolean completed, LocalDate eventDate) {
        super(name, completed);
        this.eventDate = eventDate;
    }

    /**
     * Creates a new Event object.
     * @param input Input for the Event in the form "{ name } /at { date }".
     * @return New Event object.
     */
    public static Event makeEvent(String input) throws ChecklstException {
        String[] splitInput = input.split(" /at ");
        if (splitInput.length == 1) {
            throw new ChecklstException("Inproper Event format used! Please use { name } /at { event }");
        }

        LocalDate eventDate;
        try {
            eventDate = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new ChecklstException("Incorrect Event Date format! Please use YYYY-MM-DD");
        }

        return new Event(splitInput[0], eventDate);
    }

    @Override
    public Task complete() {
        return new Event(this.name, true, this.eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
