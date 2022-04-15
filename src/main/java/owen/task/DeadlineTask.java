package owen.task;

import owen.DateTime;
import owen.OwenException;

/**
 * Deadline task that need to be done before a specific date/time.
 */
public class DeadlineTask extends Task {
    private final DateTime deadline;

    /**
     * Creates new deadline task with arguments.
     *
     * @param taskArgs Arguments in the format: description /by DD/MM/YYYY HHMM.
     * @throws OwenException Arguments could not be parsed.
     */
    public DeadlineTask(String taskArgs) throws OwenException {
        super();

        String[] taskArgsSplit = taskArgs.split(" /by ", 2);

        if (taskArgsSplit.length < 2) {
            throw new OwenException("Deadline task must have a description and due date/time...");
        }

        this.description = taskArgsSplit[0];
        this.deadline = DateTime.parse(taskArgsSplit[1]);
    }

    private DeadlineTask(String description, boolean isDone, DateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public DeadlineTask markAsDone() {
        return new DeadlineTask(this.description, true, this.deadline);
    }

    @Override
    public boolean isDue(long days) {
        return this.deadline.isSoon(days) && !this.isDone;
    }

    @Override
    public String serialize() {
        String serializeFormat = "DEADLINE | %b | %s | %s";
        return String.format(
                serializeFormat, this.isDone, this.description, this.deadline.getAsInputFormat());
    }

    /**
     * Deserializes string into a DeadlineTask.
     *
     * @param string String to deserialize.
     * @return DeadlineTask deserialized from string.
     * @throws OwenException Task could not be deserialized.
     */
    public static DeadlineTask deserialize(String string) throws OwenException {
        assert string.length() > 0 : "string cannot be empty";
        String[] fields = string.split(" \\| ");
        System.out.println(fields);
        boolean isDone = Boolean.valueOf(fields[1]);
        String description = fields[2];
        DateTime deadline = DateTime.parse(fields[3]);
        return new DeadlineTask(description, isDone, deadline);
    }

    @Override
    public String toString() {
        String taskFormat = "[%s][%s] %s (by: %s)";
        return String.format(
                taskFormat,
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.deadline);
    }

    @Override
    protected String getTypeIcon() {
        return "D";
    }
}
