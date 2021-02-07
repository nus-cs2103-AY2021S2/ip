package main.java.entity;

/**
 * An abstract class for all types of tasks
 * Described by Task name and isDone status
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Creates a Task with given name but default isDone is false
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a Task with name and isDone given as input
     * @param name name of task
     * @param isDone boolean isDone status of task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Getter for Task name attribute
     * @return task name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for Task isDone status attribute
     * @return isDone
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Change isDone attribute of this task to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Provides API for Task and its sub-classes to call toString() method
     * @return Display String representation of this Task
     */
    @Override
    public abstract String toString();

    /**
     * Provides API for Task and its sub-classes to call toFileString() method
     * @return File String representation of this Task
     */
    public abstract String toFileString();
}
