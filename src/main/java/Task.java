public class Task {
    private String task;
    private Boolean completed;

    Task(String t) {
        this.task = t;
        this.completed = false;
    }

    Task(String t, Boolean completed) {
        this.task = t;
        this.completed = completed;
    }

    protected boolean getCompleted() {
        return this.completed;
    }

    protected String getTask() {
        return this.task;
    }

    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Decides how to present the box based on
     * if the task is completed
     * @return string box that is checked or unchecked
     */
    public String completedBox() {
        if (this.getCompleted()) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    boolean taskMatch(String keyword) {
        return this.getTask().contains(keyword);
    }

    public String toCommand() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.getTask();
    }

    @Override
    public String toString() {
        return this.completedBox() + this.getTask();
    }
}
