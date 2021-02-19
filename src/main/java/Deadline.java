import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "MMM d yyyy");

    private LocalDate date;

    Deadline(String description) {
        super(description);
    }

    Deadline(String description, String eventDate) {
        super(description, eventDate);
        formatDate();
    }

//    public static void main(String[] args) {
//        LocalDate date = LocalDate.parse("2019-12-01");
//
//        String formattedDate = date.format(formatter);
//        System.out.println(formattedDate);
//    }

    Deadline(String[] description) {
        super(description[0].substring(9), description[1]);
    }

    Deadline(String description, String eventDate, boolean isDone) {
        super(description, eventDate, isDone);
        formatDate();

    }

    /**
     * Method that formats the date into a standard format and stores it as local date.
     */
    public void formatDate() {
        this.date = LocalDate.parse(this.eventDate);
    }

    /**
     * Method that returns a standard formatted date.
     * @return A formatted date time string.
     */
    public String getDate() {
        try {
            return this.date.format(formatter);
        } catch (Exception e) {
            return this.eventDate;
        }
    }


    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns formatted string of deadline.
     * @return Formatted String representing deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s(by: %s)", this.getTaskType(),
                this.getStatusIcon(), this.getDescription(), this.getDate());
    }


}
