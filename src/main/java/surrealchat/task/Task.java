package surrealchat.task;

//Below solution adapted from partial solution in:
// https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html
/**
 * Represents an abstract task, which task subclasses inherit from.
 */
public abstract class Task {
    protected final String description;
    protected final String type;
    protected final boolean isDone;

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
     * Gives a description of the Task.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the description of the Task.
     * @param newDescription The new description for the Task.
     * @return New Task with edited description.
     */
    public abstract Task editDescription(String newDescription);

    /**
     * Toggles the Task between done and undone.
     * @return Task marked as done/undone.
     */
    public abstract Task markAsDone();


    /**
     * Converts the Task into a string format for saving into file.
     * @return Task in string format for file storage.
     */
    public String saveTask() {
        return String.format("%s/split/%s/split/%s", this.getType(), this.getStatusInt(), this.getDescription());
    }

    /**
     * Converts the Task into a string format for display on user output.
     * @return Task in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), this.getStatusIcon(), this.getDescription());
    }
}
