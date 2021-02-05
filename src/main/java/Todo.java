public class Todo extends Task {

    /**
     * Main constructor.
     * @param description Description of task.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Getter method that retrieves the type of the task.
     * @return String representing task type.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s ", this.getTaskType(),
                this.getStatusIcon(), this.getDescription());
    }
}
