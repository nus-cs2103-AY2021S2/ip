package duke;

/**
 * Represents a Task. Stores relevant information about the task like  basic description, the code letter that will
 * be displayed when the Task is printed, as well as the state of whether or not the Task is done.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String letterCode;

    /**
     * Constructor.
     * @param description the description of the Task
     * @param letterCode the letter Code to indicate the type of Task ( for text-ui displaying )
     */

    public Task(String description, String letterCode) {
        this.description = description;
        this.letterCode = letterCode;
        this.isDone = false;
    }

    /**
     * Gives a symbol to indicate the state of whether the task is done.
     * tick means the task is done. X means the task is not yet done .
     * @return tick or X symbol
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * set the state of the task to be done.
     */

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the letter code for the task ( for example , 'T' for ToDo tasks );
     * @return letter code of the type of Task
     */

    public String getLetterCode() {
        return this.letterCode;
    }

    /**
     * Returns the task in the string representation used to save the Task on the hard disk.
     * (e.g letterCode | (1 or 0 depending on if task completed or not) | description of Task)
     * @return the string representing the task in saved format.
     */

    public String getSavedStringFormat() {
        String s = (isDone) ? "1" : "0";
        return letterCode + " | " + s + " | " + description;
    }

    /**
     * Return the string representation of Task ( normally for displauing purposes).
     * for example the format [letterCode][ "X "if done else ""][description]
     * @return string representation of Task
     */

    @Override
    public String toString() {
        String mark = (isDone ? "X" : " ");
        return "[" + this.getLetterCode() + "]" + "[" + mark + "] " + description;
    }

    public String getDescription() {
        return this.description;
    }

}
