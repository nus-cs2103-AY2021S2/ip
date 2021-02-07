package data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Task is the parent class for all other types of tasks. The JsonTypeInfo is to
 * enable Jackson to properly serialize and deserialize with json. The property
 * "d" acts as a discrimator for the type of task in json so that Jackson can
 * reconstruct the correct task type from json.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "d")
@JsonSubTypes({ @Type(value = Todo.class, name = "Todo"), @Type(value = Deadline.class, name = "Deadline"),
        @Type(value = Event.class, name = "Event") })
public class Task {

    @JsonProperty
    protected boolean isDone;
    @JsonProperty
    protected String description;
    @JsonProperty
    protected TagList tags;

    protected Task() {
        tags = new TagList();
    }

    /**
     * Task constructor
     *
     * @param description
     */
    public Task(String description, TagList tags) {
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    /**
     * Gets the status icon char for the task state
     *
     * @return status icon
     */
    @JsonIgnore
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * return if the tasks is done
     *
     * @return task's isDone value
     */
    @JsonIgnore
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the tasks as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the tags of the task
     *
     * @return tags
     */
    public TagList getTags() {
        return tags;
    }

    /**
     * Returns a task as a string with status icon
     *
     * @return task string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(getStatusIcon()).append("] ").append(getDescription());

        for (Tag tag : tags) {
            stringBuilder.append(" #").append(tag.getName());
        }

        return stringBuilder.toString();
    }
}
