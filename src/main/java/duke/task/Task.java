/**
 * This class is the class of tasks that is going to be added to Duke
 *
 * @param description Task description
 * @param isDone Task status of whether task has been done
 *
 * @author WangYihe
 * @author E0424695
 */

package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * check whether task is done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Printing format
     */
    public String toString() {
        String str = "";
        if (this.isDone) {
            str = "[X] " + this.description;
        } else {
            str = "[ ] " + this.description;
        }
        assert str.length() >= 3 : "Printing error, string less than 3 char";
        return str;
    }

    /**
     * change to saved format
     */
    public String savedFormat() {
        String savedInfo;
        if (this.isDone()) {
            savedInfo = "T | 1 | " + this.getDescription();
        } else {
            savedInfo = "T | 0 | " + this.getDescription();
        }
        return savedInfo;
    }
}
