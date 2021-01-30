package tasks;

public class DukeTask {
    protected final String name;
    protected final boolean isDone;
    protected final TaskType type;

    enum TaskType {
        TODO("[T]"),
        DEADLINE("[D]"),
        EVENT("[E]");

        private final String type;

        TaskType(String code) {
            this.type = code;
        }

        @Override
        public String toString() {
            return this.type;
        }

        public Character toType() {
            return this.toString().charAt(1);
        }
    }

    public DukeTask(String name, TaskType type) {
        this(name, false, type);
    }

    /**
     * Constructor for DukeTask.
     * @param name Name of the DukeTask.
     * @param isDone Boolean of whether Task is done or not.
     * @param type Type of the DukeTask.
     */
    public DukeTask(String name, boolean isDone, TaskType type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Mark the DukeTask as done.
     *
     * @return a new DukeTask who is marked as done.
     */
    public DukeTask markDone() {
        return new DukeTask(this.name, true, this.type);
    }

    public String getStatusIcon() {
        return (this.isDone) ? "[X]" : "[ ]";
    }

    /**
     * Formats the DukeTask into a String for the load file.
     * @return a formatted String.
     */
    public String formatDuke() {
        Character task = this.type.toType();
        int done = (this.isDone) ? 1 : 0;
        return String.format("%s | %d | %s", task, done, this.name);
    }

    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return this.type + this.getStatusIcon() + " " + this.name;
    }
}
