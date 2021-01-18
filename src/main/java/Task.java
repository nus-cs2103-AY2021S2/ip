public class Task {
    protected String name;
    protected boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }
    public void checkTask() {
        this.completed = true;
    }

    public String getStatusIcon() {
        return (completed ? "\u2713" : ""); //return tick or blank
    }
}
