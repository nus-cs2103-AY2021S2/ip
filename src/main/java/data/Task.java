package data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "d")
@JsonSubTypes({
    @Type(value = Todo.class, name = "Todo"),
    @Type(value = Deadline.class, name = "Deadline"),
    @Type(value = Event.class, name = "Event")
})
public class Task {
    @JsonProperty
    protected boolean isDone;
    @JsonProperty
    protected String description;

    protected Task() {}

    /**
     * Task constructor
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @JsonIgnore
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    @JsonIgnore
    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
