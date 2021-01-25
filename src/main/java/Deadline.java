public class Deadline extends Task {
    protected String by;

    public Deadline(String description, TaskType taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    @Override
    public String saveTaskString() {
        String delimiter = " ~ ";
        return super.saveTaskString() + delimiter + this.by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
