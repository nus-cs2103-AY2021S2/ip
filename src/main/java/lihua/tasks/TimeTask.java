package lihua.tasks;

import java.time.LocalDate;

public class TimeTask extends Task {
    private final LocalDate date;

    /**
     * Initializes a task with a date and a name.
     *
     * @param name The name of the task.
     * @param date The date of the task.
     */
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
