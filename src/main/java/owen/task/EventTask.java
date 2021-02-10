package owen.task;

import owen.DateTime;
import owen.OwenException;

/**
 * Event task that start and ends at specific times.
 */
public class EventTask extends Task {
    private final DateTime start;
    private final DateTime end;

    /**
     * Creates new event task with arguments.
     *
     * @param taskArgs Arguments in the format: description /at DD/MM/YYYY HHMM - DD/MM/YYYY HHMM.
     * @throws OwenException Arguments could not be parsed.
     */
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
    public boolean isDue(long days) {
        return this.start.isSoon(days) && !this.isDone;
    }

    @Override
    public String serialize() {
        String serializeFormat = "EVENT | %b | %s | %s | %s";
        return String.format(
                serializeFormat,
                this.isDone,
                this.description,
                this.start.getAsInputFormat(),
                this.end.getAsInputFormat());
    }

    /**
     * Deserializes string into a EventTask.
     *
     * @param string String to deserialize.
     * @return EventTask deserialized from string.
     * @throws OwenException Task could not be deserialized.
     */
    public static EventTask deserialize(String string) throws OwenException {
        assert string.length() > 0 : "string cannot be empty";
        String[] fields = string.split(" \\| ");
        boolean isDone = Boolean.valueOf(fields[1]);
        String description = fields[2];
        DateTime start = DateTime.parse(fields[3]);
        DateTime end = DateTime.parse(fields[4]);
        return new EventTask(description, isDone, start, end);
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
