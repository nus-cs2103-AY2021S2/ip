public class Task {
    
    private String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + task;
    }
    
}
