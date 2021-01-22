//Tasks Class that is parent of deadline, Events and Todo
public abstract class Task {
    protected String taskName;
    protected boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }
    public void markCompleted(){
        completed = true;
    }

    @Override
    public String toString() {
        return (completed ? "[X] "+taskName : "[ ] "+taskName);
    }
}
