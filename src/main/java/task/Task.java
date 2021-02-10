package task;

import tag.Tag;

import java.util.ArrayList;

/**
 * Task object.
 */
public class Task {
    /**
     * Task description.
     */
    protected String description;
    /**
     * Task completion status.
     */
    protected boolean isDone;

    /**
     * List of tag this task is tagged to.
     */
    protected ArrayList<Tag> tagList;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagList = new ArrayList<>();
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        assert this.isDone : "Unable to mark task as done!";
    }

    /**
     * Retrieves status string.
     * r
     *
     * @return status icon. A check mark for complete tasks , an x mark otherwise.
     */
    public String getStatusIcon() {
        return (isDone
                ? "\u2713"
                : "\u2718");
    }

    /**
     * Gets task description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets task's tag list.
     *
     * @return the task's tag list
     */
    public ArrayList<Tag> getTagList() {
        return this.tagList;
    }

    public String getTags() {
        StringBuilder tags = new StringBuilder();
        this.tagList.stream().forEach((tag) -> tags
                .append(tag.tagName)
                .append(" "));
        return tags.toString();
    }

    @Override
    public String toString() {
        return this.description + " " + getStatusIcon() + " " + getTags();
    }
}
