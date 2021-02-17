package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Custom date class that wraps a java.time.LocalTime object. Should only be called by KiwiDateTime class.
 */
public class KiwiDate {
    private final LocalDate date;

    private KiwiDate(LocalDate ld) {
        this.date = ld;
    }

    public static KiwiDate of(int day, int month) {
        return new KiwiDate(LocalDate.of(LocalDateTime.now().getYear(), month, day));
    }

    @Override
    public String toString() {
        if (date.getYear() == LocalDate.now().getYear()) {
            return String.format("%d/%d",
                    date.getDayOfMonth(),
                    date.getMonth().getValue());
        } else {
            return String.format("%d/%d/%d",
                    date.getDayOfMonth(),
                    date.getMonth().getValue(),
                    date.getYear());
        }
    }

    public static void main(String[] args) {
        KiwiDate a = KiwiDate.of(4, 7);
        KiwiDateTime.print(a);
    }
}
