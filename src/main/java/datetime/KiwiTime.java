package datetime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Custom time class that wraps a java.time.LocalTime object. Should only be called by KiwiDateTime class.
 */
public class KiwiTime {
    private LocalTime time;
    private boolean isEmpty; // i.e. user did not set time
    private boolean isHourOnly;

    KiwiTime() {
        this.isEmpty = true;
    }

    // hmm what happens when users want to edit time variables
    KiwiTime(int hour, int minute) {
        this.time = LocalTime.of(hour, minute);
        this.isEmpty = false;
        checkHourOnly();
    }

    public static KiwiTime of(int hour, int minute) {
        return new KiwiTime(hour, minute);
    }

    public static KiwiTime of(int hour) {
        return new KiwiTime(hour, 0);
    }

    public static KiwiTime ofEmpty() {
        return new KiwiTime();
    }

    private void checkHourOnly(){
        this.isHourOnly = (this.time.getMinute() == 0);
    }

    // todo printing AM or PM
    @Override
    public String toString() {
        if (isEmpty) {
            return "";
        }

        DateTimeFormatter f;

        if (isHourOnly) {
            f = DateTimeFormatter.ofPattern("ha");
        } else {
            f = DateTimeFormatter.ofPattern("h:ma");
        }

        return this.time.format(f).toLowerCase();
    }

    public static void main(String[] args) {
        KiwiTime t0 = new KiwiTime();
        KiwiTime t1 = new KiwiTime(6, 12);
        KiwiTime t2 = KiwiTime.of(8);
        KiwiTime t3 = new KiwiTime(14, 40);
        KiwiDateTime.print(
                t0, t1, t2, t3
        );
    }

    public String unparse(String delimiter) {
        return String.format("%d%s%d", this.time.getHour(), delimiter, this.time.getMinute());
    }

}
