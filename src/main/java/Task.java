package surrealchat.task;

//Below solution adapted from partial solution in: https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html
/**
 * Represents an abstract task, which task subclasses inherit from.
 */
public abstract class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    /**
     * Creates instance of Task object.
     * @param description Description of task.
     * @param type Type in terms of todo (denoted "T"), deadline (denoted "D"), event (denoted "E").
     * @param isDone Boolean flag of whether task is done or not.
     */
    public Task(String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Converts status of isDone into integer.
     * @return 1 if task was completed and 0 if task was not completed.
     */
    public int getStatusInt() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Gives an icon displaying whether task was completed.
     * @return Tick if task was completed and cross if task was not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gives a description of the task.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new UnsupportedOperationException("This task is already done.\n" +
                    "I would have wanted to say Stonks...\n" +
                    "but your usage of an illegal operation is Not Stonks!");
        }
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new UnsupportedOperationException("This task is already not done. Not stonks anyway!");
        }
    }

    /**
     * Converts the task into a string format for saving into file.
     * @return Task in string format for file storage.
     */
    public String saveTask() {
        return String.format("%s/split/%s/split/%s", this.getType(), this.getStatusInt(), this.getDescription());
    }

    /**
     * Converts the task into a string format for display on user output.
     * @return Task in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), this.getStatusIcon(), this.getDescription());
    }
}