package owen.task;

import owen.OwenException;

/**
 * Todo task without any date/time attached to it.
 */
public class TodoTask extends Task {
    /**
     * Creates new todo task with arguments.
     *
     * @param taskArgs Arguments in the format: description.
     * @throws OwenException Arguments could not be parsed.
     */
    public TodoTask(String taskArgs) throws OwenException {
        super(taskArgs);

        if (taskArgs.equals("")) {
            throw new OwenException("The description of a todo cannot be empty...");
        }
    }

    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public TodoTask markAsDone() {
        return new TodoTask(this.description, true);
    }

    @Override
    public boolean isDue(long days) {
        return false;
    }

    @Override
    public String serialize() {
        String serializeFormat = "TODO | %b | %s";
        return String.format(serializeFormat, this.isDone, this.description);
    }

    /**
     * Deserializes string into a TodoTask.
     *
     * @param string String to deserialize.
     * @return TodoTask deserialized from string.
     */
    public static TodoTask deserialize(String string) {
        assert string.length() > 0 : "string cannot be empty";
        String[] fields = string.split(" \\| ");
        boolean isDone = Boolean.valueOf(fields[1]);
        String description = fields[2];
        return new TodoTask(description, isDone);
    }

    @Override
    public String toString() {
        String taskFormat = "[%s][%s] %s";
        return String.format(
                taskFormat,
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description);
    }

    @Override
    protected String getTypeIcon() {
        return "T";
    }
}
