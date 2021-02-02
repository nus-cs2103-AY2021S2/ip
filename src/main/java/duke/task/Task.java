package duke.task;

public abstract class Task {
    public String input;
    public boolean isDone;
    static String CHECKED = "[X]";
    static String UNCHECKED = "[ ]";

    public Task(String input) {
        this.input = input;
        this.isDone = false;
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

    public abstract String formatToSave();

    public void checkTask() {
        this.isDone = true;
    }
}
