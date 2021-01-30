package com.lirc572.ip;

public class Task {

    /**
     * Whether the task is done
     */
    private boolean isDone;

    /**
     * The name of the task
     */
    private final String name;

    /**
     * Constructs a new Task with the specified name
     *
     * @param name The name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the name of the Task
     *
     * @return The name of the Task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether the task is done
     *
     * @return Whether the task is done
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets whether the task is done
     *
     * @param isDone Whether the task is done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Task for storage
     * @return The string representation of the Task for storage
     */
    public String toSavedString() {
        return String.format(
                "%d | %s",
                this.getIsDone() ? 1 : 0,
                this.getName()
        );
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getIsDone()? "X" : " ", this.getName());
    }
}
