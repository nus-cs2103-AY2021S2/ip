public class Task {
    
    private final String name;
    private final boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    private Task(String name, boolean completed) {
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

    public String getInformation() {
        String complete = this.completed ? "[X] " : "[ ] "; 
        return complete + this.name;
    }

}
