/**
 * A helper class that represents each unique Task that a user might wish to track.
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTasks++;
    }

    /**
     * Prints feedback text to the user that the Task has been instantiated.
     */
    public void printInstantiationText() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(this.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Returns the relevant status icon for this task.
     *
     * @return A string containing this task's status icon
     */
    public String getStatusIcon() {
        String icon = isDone ? "\u2713" : "\u2718";
        return ("[" + icon + "]"); //return tick or X symbols within square brackets
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this);
    }

    /**
     * Returns the task status icon and description.
     *
     * @return A string containing the task status icon and description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
