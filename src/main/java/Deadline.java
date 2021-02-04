import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate localDate;
    String _description;
    Deadline(String description) throws EmptyArgumentException {
        super(description);
        int indexOfDate = description.indexOf("/") + 4;
        localDate = LocalDate.parse(description.substring(indexOfDate));
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        _description = description.substring(0,indexOfDate) + date;
    }

    @Override
    public String toString() {
        return "[D]" +  "[" + (isCompleted ? "X" : " ") + "] " + _description;
    }
}
