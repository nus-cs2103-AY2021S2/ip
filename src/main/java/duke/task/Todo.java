package duke.task;

public class Todo extends Task {
    protected String at;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * change to save format
     */
    public String savedFormat() {
        String savedInfo;
        if (this.isDone()) {
            savedInfo = "T | 1 | " + this.getDescription();
        } else {
            savedInfo = "T | 0 | " + this.getDescription();
        }
        return savedInfo;
    }
}
