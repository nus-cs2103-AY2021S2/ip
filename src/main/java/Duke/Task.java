package duke;

public abstract class Task {
    public String input;
    public boolean done;
    static String CHECKED = "[X]";
    static String UNCHECKED = "[ ]";

    public Task(String input) {
        this.input = input;
        this.done = false;
    }

    public String getDescription() {
        return this.input;
    }

    public boolean hasKeyWord(String keyword) {
        String[] words = getDescription().split(" ");
        boolean containsKeyword = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(keyword)) {
                containsKeyword = true;
            }
        }
        return containsKeyword;
    }

    public abstract String formatToSave();

    public void checkTask() {
        this.done = true;
    }
}
