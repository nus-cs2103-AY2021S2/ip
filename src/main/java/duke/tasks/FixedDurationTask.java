package duke.tasks;

/**
 * Responsible for containing fixed duration tasks.
 */
public class FixedDurationTask extends Task {
    protected int duration;

    /**
     * Constructs a FixedDurationTask with the given description and duration in minutes.
     *
     * @param description Description of task.
     * @param duration Duration for task to be done in.
     */
    public FixedDurationTask(String description, int duration) {
        super(description, 'F');
        this.duration = duration;
    }

    /**
     * Returns the format of string of task to be saved.
     *
     * @return String of task to be saved.
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + " | " + this.duration;
    }

    /**
     * Returns the string of the task.
     *
     * @return string of the task.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (within: "
                + getDuration() + ")";
    }

    private String getDuration() {
        int hours = this.duration / 60;
        int minutes = this.duration % 60;
        return String.format("%d hours %02d minutes", hours, minutes);
    }


}
