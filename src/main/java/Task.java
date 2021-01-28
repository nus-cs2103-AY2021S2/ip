public class Task {
    public String input;
    public boolean done;
    static String CHECKED = "[X]";
    static String UNCHECKED = "[ ]";

    public Task(String input) {
        this.input = input;
        this.done = false;
    }

    public String formatToSave() {
        return this.input;
    }

    public void checkTask() {
        this.done = true;
    }
}
