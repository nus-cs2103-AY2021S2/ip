package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.ChecklstException;

public class Event extends Task {
    
    protected final LocalDate dateTime;

    /**
     * Factory method to create a new Event.
     * @param input - Input for the Event in the form "{ name } /at { date }".
     * @return - New Event object.
     */
    public static Event makeEvent(String input) throws ChecklstException {
        String[] splitInput = input.split(" /at ");
        if (splitInput.length == 1) {
            throw new ChecklstException("Inproper Event format used! Please use { name } /at { event }");
        }

        LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new ChecklstException("Incorrect DateTime format! Please use YYYY-MM-DD");
        }

        return new Event(splitInput[0], dateTime);
    }

    protected Event(String name, LocalDate dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    protected Event(String name, boolean completed, LocalDate dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public Task complete() {
        return new Event(this.name, true, this.dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
            + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
