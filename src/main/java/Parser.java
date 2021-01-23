import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String localDateToString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dtf);
    }
}
