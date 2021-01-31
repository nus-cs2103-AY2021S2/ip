package duke.task;

import duke.exception.EmptyArgumentException;

public abstract class Task {
    private final String description;
    public boolean isDone; //TODO: Figure out if I can restrict access

    public Task(String description) throws EmptyArgumentException {
        description = description.trim();
        if (description.isEmpty()){
            throw new EmptyArgumentException();
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "*" : " "); //Don't use unicode, cause it can't test properly
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon()+"]: " +
                description;
    }

    public abstract String toFileString();

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    String toBaseFileString(){
        return (isDone ? "1" : "0") + "," + description.length() + "," + description;
    }
}