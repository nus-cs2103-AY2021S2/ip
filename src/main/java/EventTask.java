/**
 * Event task that start and ends at specific times.
 */
public class EventTask extends Task {
    private final String startEndTime;

    public EventTask(String taskArgs) throws OwenException {
        super();

        String[] taskArgsSplit = taskArgs.split(" /at ", 2);

        if (taskArgsSplit.length < 2) {
            throw new OwenException("Event task must have a description and start/end time...");
        }

        this.description = taskArgsSplit[0];
        this.startEndTime = taskArgsSplit[1];
    }

    private EventTask(String description, boolean isDone, String startEndTime) {
        super(description, isDone);
        this.startEndTime = startEndTime;
    }

    @Override
    public EventTask markAsDone() {
        return new EventTask(this.description, true, this.startEndTime);
    }

    @Override
    public String toString() {
        String taskFormat = "[%s][%s] %s (at: %s)";
        return String.format(
                taskFormat,
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.startEndTime);
    }

    @Override
    protected String getTypeIcon() {
        return "E";
    }
}
