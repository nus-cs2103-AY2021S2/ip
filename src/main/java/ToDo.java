/**
 * Represents a task which needs to be done.
 */
public class ToDo extends Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for ToDo which has not been done.
     * @param name Name of task.
     */
    ToDo(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for ToDo.
     * @param name Name of task.
     * @param done Indicates if task has been done.
     */
    ToDo(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    /**
     * Marks task as done.
     * @return Completed task.
     */
    @Override
    Task done() {
        return new ToDo(this.name, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Task set(String date) {
        return this;
    }

    /**
     * Checks the equivalence of ToDo this and Object obj.
     * If obj is an instance of the ToDo class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return toDo.name.equals(this.name) && (toDo.isDone == this.isDone);
        }
        return false;
    }

    /**
     * Represents a ToDo instance in String.
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.name;
        }
        return "[T][ ] " + this.name;
    }
}
