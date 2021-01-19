/**
 * Implementation for the Tasks that the chat-bot stores.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task setDone() {
        this.isDone = true;
        return this;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.description;
    }
}
