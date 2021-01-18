public class Task {
    
    protected final String name;
    protected final boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    protected Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public Task completeTask() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
        String complete = this.completed ? "[X] " : "[ ] "; 
        return "[T]" + complete + this.name;
    }

}
