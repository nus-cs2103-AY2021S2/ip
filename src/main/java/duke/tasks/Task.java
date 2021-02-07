package duke.tasks;

public class Task {
    protected String info;
    protected boolean isDone;
    protected taskType type;
    private String output;

    /**
     * Default constructor used when a new task is added
     *
     * @param info, name of the task
     * @param type, the taskType for classification
     */
    public Task(String info, taskType type) {
        this.info = info;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Secondary constructor used when a task is loaded from .txt file
     *
     * @param info, name of the task
     * @param type, the taskType for classification
     * @param isDone, boolean value to indicate whether task is done
     */
    public Task(String info, taskType type, boolean isDone) {
        this.info = info;
        this.type = type;
        this.isDone = isDone;
    }

    public String getInfo() {
        return this.info;
    }

    public String checkIcon() {
        if (isDone) {
            return "\u0058"; // icon is a cross
        } else {
            return " ";
        }
    }

    public void markCompleted() {
        isDone = true;
    }

    public taskType getType() {
            return this.type;
    }

    public String toSave() {
        return this.getType().toString() + " " + this.getInfo();
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String output = "[" + checkIcon() + "]" + this.info;
        return output;
    }
}