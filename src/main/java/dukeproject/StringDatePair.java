package dukeproject;

import java.time.LocalDateTime;

public class StringDatePair {
    private final String stringVal;
    private final LocalDateTime dateTimeVal;

    StringDatePair(String stringVal, LocalDateTime dateTimeVal) {
        this.stringVal = stringVal;
        this.dateTimeVal = dateTimeVal;
    }

    // Returns the string value
    public String getString() {
        return stringVal;
    }

    // Return the date value
    public LocalDateTime getDate() {
        return dateTimeVal;
    }
}
