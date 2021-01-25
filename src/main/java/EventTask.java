/**
 * Event task that start and ends at specific times.
 */
public class EventTask extends Task {
    private final DateTime start;
    private final DateTime end;

    public EventTask(String taskArgs) throws OwenException {
        super();

        String[] taskArgsSplit = taskArgs.split(" /at ", 2);

        if (taskArgsSplit.length < 2) {
            throw new OwenException("Event task must have a description and start/end time...");
        }

        String[] startEndSplit = taskArgsSplit[1].split(" - ", 2);

        if (startEndSplit.length < 2) {
            throw new OwenException("Both start and end date/time must be specified...");
        }

        this.description = taskArgsSplit[0];
        this.start = DateTime.parse(startEndSplit[0]);
        this.end = DateTime.parse(startEndSplit[1]);
    }

    private EventTask(String description, boolean isDone, DateTime start, DateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public EventTask markAsDone() {
        return new EventTask(this.description, true, this.start, this.end);
    }

    @Override
    public String toString() {
        String taskFormat = "[%s][%s] %s (at: %s - %s)";
        return String.format(
                taskFormat,
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.start,
                this.end);
    }

    @Override
    protected String getTypeIcon() {
        return "E";
    }
}
