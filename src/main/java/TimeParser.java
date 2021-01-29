import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeParser {
    private LocalDateTime time;

    TimeParser() {

    }

    TimeParser(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime parse(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime time = LocalDateTime.parse(input, formatter);
        return time;
    }
}
