package task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "d")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Todo.class, name = "Todo"),
        @JsonSubTypes.Type(value = Deadline.class, name = "Deadline"),
        @JsonSubTypes.Type(value = Event.class, name = "Event")
})

public class Task {
    protected String taskName;
    protected boolean done;

    protected Task() {
    }

    /**
     * Constructs a Task object.
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }
}

