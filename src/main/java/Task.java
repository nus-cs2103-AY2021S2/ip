public class Task {
    
    private final String name;
    private final boolean completed;

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Task complete() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
        String completed = this.completed ? "[X]" : "[]";
        return completed + " " + this.name;
    }

}
