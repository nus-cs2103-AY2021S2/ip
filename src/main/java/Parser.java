import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime parseInputDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
    }

    public static LocalDateTime parseFileDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    public
}