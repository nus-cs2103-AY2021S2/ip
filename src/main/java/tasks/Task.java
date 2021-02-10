package tasks;

public abstract class Task {
    protected static String delimiter = ";;"; // todo make sure no one uses this reserved delimiter for tasks
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Creates simple task with one field
     * @param description A string describing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor used by parsing functions when saving from hard drive
     * @param desc task description
     * @param isDone completion status of task
     */
    protected Task(String desc, Boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    /**
     * Gets the symbol that represents if the object is completed.
     * @return A string that represents if the task is completed or not.
     */
    protected String getStatusIcon() {
        return (isDone ? "/" : " ");
    }

    /**
     * Marks the task as done so that a done symbol shows up in the task's toString.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats a string representing the task object to be saved to the hard drive.
     * @return String representation of task object for storage file
     */
    public abstract String unparse();

    // parse from text file to become a task object todo - this needs to be static so can't be abstract
    // public abstract tasks.Task parse(String oneLine);


    /**
     * Description asked for tasklist.find. Todo maybe contains method should be brought in here instead
     * @return
     */
    public String getDescription() {
        return description;
    }
}
