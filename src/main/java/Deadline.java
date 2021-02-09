import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * represents a task to be completed by a certain time or the deadline of the task
 */
public class Deadline extends Task {

    LocalDate localDate;

    String formattedDescription;

    String dateString;

    /**
     *
     * @param description the description of the deadline tasl
     * @throws EmptyArgumentException empty argument exception
     * @throws BadDateException bad date exception
     */
    Deadline(String description) throws EmptyArgumentException, BadDateException {
        super(description);
        int indexOfDate = description.indexOf("/") + 4;
        try {
            localDate = LocalDate.parse(description.substring(indexOfDate));
            dateString = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            formattedDescription = description.substring(0, indexOfDate) + dateString;
        }
        catch(DateTimeParseException e) {
            throw new BadDateException();
        }
    }
    public String getDateString() {
        return dateString;
    }

    /**
     *
     * @return the string representation of a deadline object
     */
    @Override
    public String toString() {
        return "[D]" +  "[" + (isCompleted ? "X" : " ") + "] " + formattedDescription;
    }
}
