package duke;

/**
 *  The Task class contains methods for tasks
 */
public class Task {


    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Gets status Icon as a string
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks Task as done, changes icon from x to tick
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true is task contains keyword
     * @return boolean value whether task contains keyword
     */
    public boolean hasKeyWord(String keyword){
        String[] words = this.description.split(" ");
        boolean containsKeyword = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(keyword)) {
                containsKeyword = true;
            }
        }
        return containsKeyword;

    }

    /**
     * Represents Tasks object as a String
     */
    public String toString() {
        return "[" +this.getStatusIcon() + "] " + this.description;
    }
}
