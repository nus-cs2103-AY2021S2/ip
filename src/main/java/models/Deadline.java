package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Todo {
    private static final DateTimeFormatter inputDateFormat =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter outputDateFormat =
            DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm");
    /** String denoting the deadline of a Deadline object */
    protected String deadlineString;
    protected LocalDateTime deadlineDateTime;

    /**
     * Constructor to allow setting of the deadline in a Deadline object
     * 
     * @param message String message that a Todo contains
     * @param deadlineString String specific to deadlines denoting when the deadline is due
     */
    public Deadline(String message, String deadlineString) {
        super(message);
        this.deadlineString = deadlineString;
        this.deadlineDateTime = parseStringToLocalDateTime(deadlineString);
    }

    /**
     * Constructor to allow setting of the isDone attribute of an Deadline
     * 
     * @param message String message that a Todo contains
     * @param isDone boolean denoting if a Todo is done
     * @param deadlineString String specific to deadlineStrings denoting the deadlineString of a
     *        Deadline Todo
     */
    public Deadline(String message, boolean isDone, String deadlineString) {
        super(message, isDone);
        this.deadlineString = deadlineString;
        this.deadlineDateTime = parseStringToLocalDateTime(deadlineString);
    }

    /**
     * Expects String in the format dd/MM/yyyy HHMM and returns LocalDateTime object
     * 
     * @param deadlineString String passed in with the format dd/MM/yyyy HHMM
     * @return LocalDateTime object from String deadlineString passed in
     * @throws DateTimeParseException when date time is in the wrong format
     */
    private LocalDateTime parseStringToLocalDateTime(String deadlineString)
            throws DateTimeParseException {
        // @formatter:off
        return LocalDateTime.parse(deadlineString.length() == 15 
                ? deadlineString
                : String.format("0%s", deadlineString), inputDateFormat);
    }

    /**
     * Getter method for deadline of a Deadline object
     * 
     * @return String containing deadline of the Deadline
     */
    public String getDeadline() {
        return this.deadlineDateTime.format(inputDateFormat);
    }

    /**
     * Getter method for deadline time from LocalDateTime of a Deadline object
     * 
     * @return String containing deadline of the Deadline object, parsed from LocalDateTime object
     *         in a prettier format
     */
    public String getPrettierDeadlineDateTime() {
        return String.format("%s hrs", this.deadlineDateTime.format(outputDateFormat));
    }

    @Override
    /**
     * Method overriden from Todo's getMessage method to return Deadline type and deadline
     * 
     * @return String to be rendered to give information on the Deadline
     */
    public String getMessage() {
        return String.format("[D][%s] %s (by: %s)", this.getIsDoneIcon(), this.message,
                this.getPrettierDeadlineDateTime());
    }

    @Override
    /**
     * Method overridden the super class' to return a new Deadline that is marked as done instead of
     * a new Todo
     * 
     * @return Deadline that is marked as done
     */
    public Deadline markAsDone() {
        return new Deadline(this.message, true, this.getDeadline());
    }
}
