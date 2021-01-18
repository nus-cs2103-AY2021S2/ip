public class Task {
    String job;
    Boolean done;
    public Task(String job) {
        this.job = job;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    public String getJob() {
        return this.job;
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
