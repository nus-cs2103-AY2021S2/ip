package duke.task;

import duke.exception.EmptyArgumentException;
import duke.exception.BadDateArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws EmptyArgumentException, BadDateArgumentException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            LocalDate date = LocalDate.parse(by, formatter);
            this.by = date;
        }catch(DateTimeParseException e){
            throw new BadDateArgumentException();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return "[D]" + super.toString() + " (Deadline: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String date = by.format(formatter);
        return "D," + super.toBaseFileString() + "," + date.length() + "," + date;
    }
}