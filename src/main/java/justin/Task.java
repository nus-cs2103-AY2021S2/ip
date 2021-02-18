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
        return (isDone ? "COMPLETED" : "INCOMPLETE"); //return tick or X symbols
    }


    // method to mark task as done
    public void markAsDone() {
        assert this.isDone == false : "Task is already done";
        this.isDone = true;
    }

    /**
     * This method returns whether the task is done
     * @return boolean value of done status
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * This method returns the string of description of task
     * @return the description of task
     */

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}