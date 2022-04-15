package duke.util;

public class Task {
    protected final String job;
    protected final Boolean isDone;
    public Task(String job) {
        this.job = job;
        this.isDone = false;
    }

    public Task(String job, Boolean isDone) {
        this.job = job;
        this.isDone = isDone;
    }

    /**
     * Return a new Task object after Task is executed.
     */
    public Task doTask() {
        return new Task(this.job, true);
    }

    /**
     * Job getter.
     */
    public String getJob() {
        return this.job;
    }

    /**
     * Done status getter.
     */
    public Boolean getDoneStatus() {
        return this.isDone;
    }

    public String toString() {
        String status = "";
        if (isDone) {
            status = "[X] ";
        }
        else {
            status = "[ ] ";
        }
        return status + job;
    }
}
