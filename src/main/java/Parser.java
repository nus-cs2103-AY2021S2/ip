import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
    }
}
