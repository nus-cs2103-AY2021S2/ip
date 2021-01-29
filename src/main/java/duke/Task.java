package duke;

public abstract class Task {
    public String input;
    public boolean isDone;
    static String CHECKED = "[X]";
    static String UNCHECKED = "[ ]";

    public Task(String input) {
        this.input = input;
        this.isDone = false;
    }

    public abstract String formatToSave();

    public void checkTask() {
        this.isDone = true;
    }
}
