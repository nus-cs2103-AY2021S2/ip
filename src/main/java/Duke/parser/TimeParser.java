package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses string time into LocalDateTime object.
 */
public class TimeParser {
    private LocalDateTime time;
    /**
     * Returns LocalDateTime from String input.
     *
     * @param input
     * @return LocalDataTime
     */
    public LocalDateTime parse(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime time = LocalDateTime.parse(input, formatter);
        return time;
    }
}
