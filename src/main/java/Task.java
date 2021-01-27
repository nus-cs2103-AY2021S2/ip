public class Task {
    protected final String job;
    protected final Boolean done;
    public Task(String job) {
        this.job = job;
        this.done = false;
    }

    public Task(String job, Boolean done) {
        this.job = job;
        this.done = done;
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
    public Boolean getDone() {
        return this.done;
    }

    public String toString() {
        String status = "";
        if (done) {
            status = "[X] ";
        }
        else {
            status = "[ ] ";
        }
        return status + job;
    }
}
