package popo.tasks;

import java.time.LocalDate;

import popo.utils.OutputDateTimeFormat;

/**
 * Represents a task with a starting date and an ending date.
 */
public class PeriodTask extends Task {
    public static final String IDENTIFIER = "P";

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a {@code PeriodTask} object with the given task name, starting date and ending date,
     * with the task set to initially not completed.
     *
     * @param name      Name of the task.
     * @param startDate Starting date of the task.
     * @param endDate Ending date of the task.
     */
    public PeriodTask(String name, LocalDate startDate, LocalDate endDate) {
        super(IDENTIFIER, name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a {@code PeriodTask} object with the given task name, starting date and ending date,
     * with the task set to the boolean isCompleted.
     *
     * @param name        Name of the task.
     * @param isCompleted Boolean indicating whether the task has been completed.
     * @param startDate   Starting date of the task.
     * @param endDate Ending date of the task.
     */
    public PeriodTask(String name, boolean isCompleted, LocalDate startDate, LocalDate endDate) {
        super(IDENTIFIER, name, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the starting date in a nicely formatted string.
     *
     * @return Formatted starting date string.
     */
    public String getStartDate() {
        return startDate.format(OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
    }

    /**
     * Returns the ending date in a nicely formatted string.
     *
     * @return Formatted ending date string.
     */
    public String getEndDate() {
        return endDate.format(OutputDateTimeFormat.OUTPUT_DATE_FORMAT);
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + " (period: " + getStartDate() + " - " + getEndDate() + ")";
    }
}
