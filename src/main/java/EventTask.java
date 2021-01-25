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
    public String serialize() {
        String serializeFormat = "EVENT | %b | %s | %s";
        return String.format(serializeFormat, this.isDone, this.description, this.startEndTime);
    }

    /**
     * Deserialize string into a EventTask.
     * @param string String to deserialize.
     * @return EventTask deserialized from string.
     */
    public static EventTask deserialize(String string) {
        String[] fields = string.split(" \\| ");
        boolean isDone = Boolean.valueOf(fields[1]);
        String description = fields[2];
        String startEndTime = fields[3];
        return new EventTask(description, isDone, startEndTime);
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
