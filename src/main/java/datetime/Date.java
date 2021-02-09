package datetime;

import java.time.LocalDate;

public class Date {
    private LocalDate date;

    @Override
    public String toString() {
        if (date.getYear() == LocalDate.now().getYear()) {
            return String.format("%d/%d",
                    date.getDayOfMonth(),
                    date.getMonth());
        } else {
            return String.format("%d/%d/%d",
                    date.getDayOfMonth(),
                    date.getMonth(),
                    date.getYear());
        }
    }
}
