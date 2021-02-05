import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    LocalDate localDate;

    String formattedDescription;

    Deadline(String description) throws EmptyArgumentException, BadDateException {
        super(description);
        int indexOfDate = description.indexOf("/") + 4;
        try {
            localDate = LocalDate.parse(description.substring(indexOfDate));
            String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            formattedDescription = description.substring(0, indexOfDate) + date;
        }
        catch(DateTimeParseException e) {
            throw new BadDateException();
        }
    }

    @Override
    public String toString() {
        return "[D]" +  "[" + (isCompleted ? "X" : " ") + "] " + formattedDescription;
    }
}
