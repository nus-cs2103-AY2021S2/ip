public class ToDo extends Task {
    public ToDo(boolean markAsDone, String taskName) {
        super(markAsDone, taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
