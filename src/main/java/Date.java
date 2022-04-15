import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private LocalDate date;

    public Date(String date) {
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns the date object in a MMM dd yyyy format.
     *
     * @return A string representing the date in the object.
     */
    public String toFormattedString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
