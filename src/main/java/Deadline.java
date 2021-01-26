import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String date;
    private LocalDateTime localDate;

    public Deadline(String input, String date) {
        super(input);
        LocalDateTime lDate = new ParseDates().parseString(date);
        this.localDate = lDate;
    }

    @Override
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + localDate.format(dateTimeFormatter) + ")";
    }
}
