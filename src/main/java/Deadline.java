import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected final String by;
    public String localDateStr;
    public LocalDate localDate;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
        this.localDateStr = null;
    }

    public void dateFormatter(String ymd) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
            this.localDate = LocalDate.parse(ymd, dateTimeFormatter);
            this.localDateStr = dateTimeFormatter2.format(localDate);
        }
        catch (DateTimeParseException e) {
            throw new DukeException("\t\tDate cannot be parsed");
        }
    }

    public void dateTimeFormatter(String text) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
            this.localDateStr = formatter1.format(dateTime);
        }
        catch (DateTimeParseException e) {
            throw new DukeException("\t\tDate and time cannot be parsed");
        }
    }

    //TODO remove empty spaces, if any, when performing this.by.split(" ")
    @Override
    public String toString() {
        try {
            if (this.by.split(" ").length == 1) {
                dateFormatter(this.by);
            } else {
                dateTimeFormatter(this.by);
            }

        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        if (localDate == null) {
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + localDateStr + ")";
        }

    }
}

