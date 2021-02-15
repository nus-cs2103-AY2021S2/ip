package snom.model.task;

import snom.common.core.Messages;

/**
 * Stores task's information.
 */
public class Task {
    private String description;
    private boolean isFinished;

    /**
     * Constructs a {@code Task}
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isFinished = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasFinished() {
        return isFinished;
    }

    public String getStatusSymbol() {
        return isFinished ? Messages.SYMBOL_TICK : Messages.SYMBOL_BLANK;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Returns a string for saving purposes
     *
     * @return formatted string
     */
    public String getSaveString() {
        return (isFinished ? "true" : "false") + "," + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusSymbol() + "] " + this.description;
    }
}
