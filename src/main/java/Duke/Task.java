package Duke;

public abstract class Task {
    protected String input;
    protected boolean isDone;
    static String CHECKED = "[X]";
    static String UNCHECKED = "[ ]";

    public Task(String input) {
        this.input = input;
        this.isDone = false;
    }

    public abstract String formatToSave();

    /**
     * Marks the task as done.
     */
    public void checkTask() {
        this.isDone = true;
    }
}
