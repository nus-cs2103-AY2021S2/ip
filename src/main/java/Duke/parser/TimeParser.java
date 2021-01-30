package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses string time into LocalDateTime object.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class TimeParser {
    private LocalDateTime time;

    TimeParser() {

    }

    TimeParser(LocalDateTime time) {
        this.time = time;
    }

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
