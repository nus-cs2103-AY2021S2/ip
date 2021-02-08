package popo.tasks;

import java.time.Duration;

/**
 * Represents a task with a starting date and an ending date.
 */
public class DurationTask extends Task {
    public static final String IDENTIFIER = "DU";

    private static final long ONE_DAY_IN_SECONDS = 86400;
    private static final long ONE_HOUR_IN_SECONDS = 3600;

    private final Duration duration;

    /**
     * Creates a {@code DurationTask} object with the given task name and duration,
     * with the task set to initially not completed.
     *
     * @param name      Name of the task.
     * @param duration Duration of the task.
     */
    public DurationTask(String name, Duration duration) {
        super(IDENTIFIER, name);
        this.duration = duration;
    }

    /**
     * Creates a {@code DurationTask} object with the given task name, starting date and ending date,
     * with the task set to the boolean isCompleted.
     *
     * @param name        Name of the task.
     * @param isCompleted Boolean indicating whether the task has been completed.
     * @param duration Duration of the task.
     */
    public DurationTask(String name, boolean isCompleted, Duration duration) {
        super(IDENTIFIER, name, isCompleted);
        this.duration = duration;
    }

    /**
     * Returns the duration of the task.
     *
     * @return Duration of the task.
     */
    public String getDuration() {
        long seconds = duration.getSeconds();
        if (seconds >= ONE_DAY_IN_SECONDS) {
            return String.format("%d day(s)", duration.toDays());
        } else if (seconds >= ONE_HOUR_IN_SECONDS) {
            return String.format("%d hour(s)", duration.toHours());
        } else {
            return String.format("%d minute(s)", duration.toMinutes());
        }
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[DU]" + super.toString() + " (duration: " + getDuration() + ")";
    }
}
