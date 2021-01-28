package soonkeatneo.duke.task;

/**
 * Implementation for the Tasks that the chat-bot stores.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP
 */
public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public Task setDone() {
        this.isDone = true;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return X for completed tasks, else whitespace
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.getDescription();
    }
}
