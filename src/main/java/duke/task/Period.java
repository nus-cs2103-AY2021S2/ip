package duke.task;

import java.time.LocalDate;

public class Period extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /** Creates a task within period.
     *
     * @param description description of the period task.
     * @param startDate date of the start time.
     * @param endDate date of the end time.
     */
    public Period(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString()
                + "(from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
