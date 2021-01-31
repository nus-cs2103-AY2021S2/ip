package checklst.task;

public abstract class Task {
    
    protected final String name;
    protected final boolean completed;

    protected Task(String name) {
        this.name = name;
        this.completed = false;
    }

    protected Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public abstract Task complete();

    @Override
    public String toString() {
        String completed = this.completed ? "[X]" : "[]";
        return completed + " " + this.name;
    }

}
