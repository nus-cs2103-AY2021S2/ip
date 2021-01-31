package checklst.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import checklst.exception.ChecklstException;

public class Deadline extends Task {
    
    protected final LocalDate dueDate;

    /**
     * Factory method to create a new Deadline.
     * @param input - Input for the Deadline in the form "{ name } /by { date }".
     * @return - New Deadline object.
     */
    public static Deadline makeDeadline(String input) throws ChecklstException {
        String[] splitInput = input.split(" /by ");
        if (splitInput.length == 1) {
            throw new ChecklstException("Inproper Deadline format used! Please use { name } /by { deadline }");
        }

        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new ChecklstException("Incorrect DateTime format! Please use YYYY-MM-DD");
        }

        return new Deadline(splitInput[0], dueDate);
    }

    protected Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    protected Deadline(String name, boolean completed, LocalDate dueDate) {
        super(name, completed);
        this.dueDate = dueDate;
    }

    @Override
    public Task complete() {
        return new Deadline(this.name, true, this.dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
