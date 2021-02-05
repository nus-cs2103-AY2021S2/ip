package duke.task;

import duke.exception.EmptyArgumentException;

public abstract class Task {
    private final String description;
    private boolean isDone; //TODO: Figure out if I can restrict access

    public Task(String description) throws EmptyArgumentException {
        description = description.trim();
        if (description.isEmpty()){
            throw new EmptyArgumentException();
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * Gets the symbol for the status of the task.
     * @return Symbol representing the task status
     */
    public String getStatusIcon() {
        return (isDone ? "*" : " "); //Don't use unicode, cause it can't test properly
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon()+"]: " +
                description;
    }

    /**
     * Converts the Task into a format suitable for file system storage
     * @return A savable string.
     */
    public abstract String toFileString();

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    /**
     * Converts raw Task data common to all Tasks into
     * a format suitable for file system storage.
     * @return A common partial savable string.
     */
    String toBaseFileString(){
        return (isDone ? "1" : "0") + "," + description.length() + "," + description;
    }
}