package checklst.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import checklst.exception.ChecklstException;

/**
 * The Deadline class represents a Task with a specific Deadline.
 */
public class Deadline extends Task {

    protected final LocalDate dueDate;

    protected Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    protected Deadline(String name, boolean completed, LocalDate dueDate) {
        super(name, completed);
        this.dueDate = dueDate;
    }

    /**
     * Creates a new Deadline object.
     * @param input Input for the Deadline in the form "{ name } /by { date }".
     * @return New Deadline object.
     */
    public static Deadline makeDeadline(String input) throws ChecklstException {
        String[] splitInput = input.split(" /by ");
        if (splitInput.length == 1) {
            throw new ChecklstException("Inproper Deadline format used! Please use { name } /by { deadline }");
        } else if (splitInput[0].equals("")) {
            throw new ChecklstException("Deadline needs a name!");
        } else if (splitInput[0].contains(";") || splitInput[1].contains(";")) {
            throw new ChecklstException("Sorry, Deadline input cannot contain a \";\"!");
        }


        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new ChecklstException("Incorrect DateTime format! Please use YYYY-MM-DD");
        }

        return new Deadline(splitInput[0], dueDate);
    }

    /**
     * Parses a saved Deadline string.
     * @param input Deadline string.
     * @return Deadline object.
     */
    public static Deadline parseDeadline(String input) {
        String[] splitInput = input.split(" ; ");
        String name = splitInput[2];
        boolean completed = splitInput[1].equals("X") ? true : false;
        LocalDate dueDate = LocalDate.parse(splitInput[3]);

        return new Deadline(name, completed, dueDate);
    }

    @Override
    public String export() {
        String output = "D ; ";

        if (this.isCompleted) {
            output += "X ; ";
        } else {
            output += "O ; ";
        }

        output += this.name + " ; " + this.dueDate.toString();

        return output;
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
