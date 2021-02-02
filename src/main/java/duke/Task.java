package duke;

public abstract class Task {
    public String input;
    private boolean isDone;
    public static String CHECKED = "[X]";
    public static String UNCHECKED = "[ ]";

    public Task(String input) {
        this.input = input;
        this.isDone = false;
    }

    public String getMark() {
        if (isDone) {
            return CHECKED;
        } else  {
            return UNCHECKED;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    private String getDescription() {
        return this.input;
    }

    /**
     * Checks if the description of a task contains a given keyword.
     *
     * @param keyword Keyword.
     * @return Boolean.
     */
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

    /**
     * Returns the task in a String format that can be saved by the file writer.
     *
     * @return Formatted string of task.
     */
    public abstract String formatToSave();

    /**
     * Marks the task as done.
     */
    public void checkTask() {
        this.isDone = true;
    }
}
