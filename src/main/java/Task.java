public class Task {
    protected String des;
    protected boolean status;

    public Task(String des) {
        this.des = des;
        this.status = false;
    }

    public String getStatus() {
        return (status? "[X]" : "[ ]");
    }

    public void markAsDone() {
        status = true;
    }

    public String toString() {
        return this.getStatus() + " " + des;
    }
}