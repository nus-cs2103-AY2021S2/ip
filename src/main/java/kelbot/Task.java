package kelbot;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private String tag = "";
    private boolean isDone;
    /**
     * Initializes Task.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    public String getName() {
        return name;
    }
    /**
     * Completes task as done.
     */
    public void complete() {
        isDone = true;
    }
    /**
     * Provides the status icon for the toString() method.
     * @return The status icon.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
    /**
     * Provides the tag for the toString() method.
     * @return The tag.
     */
    public String getStringTag() {
        if (tag.equals("")) {
            return "";
        } else {
            return " " + tag;
        }
    }
    /**
     * Checks if the task name has given keyword.
     * @param keyword The keyword to be searched.
     * @return True if the task name has given keyword, False otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return name.contains(keyword);
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + name + getStringTag();
    }
}
