package duke.model.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * the abstract task class as a base for the tasks "deadline", "event" annd "todo"
 * that requires the subclass to define <code>markAsDone()</code> and <code>getDate()</code>
 */
public abstract class ListItem {
    private final boolean isDone;
    private final String task;
    private List<String> tagList;

    /**
     * Constructor for ListItem
     *
     * @param task takes in string and pass to parent's constructor as the task name
     */
    public ListItem(String task) {
        // check if the task name is empty or not
        assert !task.isEmpty();
        this.task = task;
        this.isDone = false;
        this.tagList = new ArrayList<>();
    }

    /**
     * Overloaded version of Constructor for ListItem
     *
     * @param task   takes in string and pass to parent's constructor as the task name
     * @param isDone takes in the task's status
     */
    public ListItem(String task, boolean isDone) {
        // check if the task name is empty or not
        assert !task.isEmpty();
        this.task = task;
        this.isDone = isDone;
        this.tagList = new ArrayList<>();
    }

    /**
     * Overloaded version of Constructor for ListItem with tag list provided
     */
    public ListItem(String task, boolean isDone, List<String> inputTagList) {
        this.task = task;
        this.isDone = isDone;
        this.tagList = inputTagList;
    }

    /**
     * @return a boolean of the task's status,
     * useful for subclasses that passed the status to parent (this class)
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String getTask() {
        return this.task;
    }

    public abstract ListItem markAsDone();

    public void addNewTagMutable(String tagName) {
        tagList.add(tagName);
    }

    /**
     * Check if any tasks in the list is associate with the tag that the user provided
     *
     * @param inputTag the tag name that user wants to find
     * @return
     */
    public boolean containTag(String inputTag) {
        return tagList.contains(inputTag);
    }

    /**
     * Print all the tags associated using stream
     *
     * @return the string concatenated all the tags
     */
    public String printTags() {
        return tagList.stream().map((x) -> " #" + x).collect(Collectors.joining());
    }

    public abstract String getDate();

    @Override
    public String toString() {
        return (isDone == true ? "[X] " : "[ ] ") + this.task;
    }
}
