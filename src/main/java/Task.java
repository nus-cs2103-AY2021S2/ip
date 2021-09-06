/**
 * Represents a Task input by users.
 * A Task is represented by a name in the form of a string
 * and a boolean to determine if it has been completed.
 */
public class Task {

    protected String name;
    protected String tag;
    protected boolean isDone;
    protected boolean isThereTag;

    /**
     * Creates a new instance of a Task.
     *
     * @param name Description of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.isThereTag = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setTag(String tag) {
        this.isThereTag = true;
        this.tag = tag;
    }

    public String getTag() {
        return "#" + this.tag;
    }

    public String getName() {
        return this.name;
    }

}
