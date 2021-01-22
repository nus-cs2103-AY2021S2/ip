package task;

public abstract class Task {
    private String taskName;
    private boolean done;

    Task (String taskName){
        this(taskName, false);
    }

    Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString() {
        String checkBox = this.done ? "[X]" : "[ ]";
        return checkBox + " " + this.taskName;
    }

    public abstract String parseToCSVRow();
}
