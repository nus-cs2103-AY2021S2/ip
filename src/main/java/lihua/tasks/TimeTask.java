package lihua.tasks;

import java.time.LocalDate;

public class TimeTask extends Task {
    private final LocalDate date;

    public TimeTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return date;
    }
}
