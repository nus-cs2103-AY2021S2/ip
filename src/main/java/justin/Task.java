package justin;

/**
 * This class create a Task that contains
 * description in String
 * and
 * boolean operator that holds the completion
 * status of the task
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * This method creates a Task class with
     * a String description
     *
     * @param description of the given task
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method gets the boolean isDone of
     * a task and returns the status
     * in ticks or crosses
     *
     * @return a String indicated completion of task
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    // method to mark task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // method to return isDone condition
    public boolean getDone() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}