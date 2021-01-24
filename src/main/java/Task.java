//Tasks Class that is parent of deadline, Events and Todo
public abstract class Task {
    protected String taskName;
    protected boolean isComplete;

    public Task(String taskName) {
        this.taskName = taskName;
        isComplete = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void markComplete(){
        isComplete = true;
    }


    public boolean isComplete(){
        return isComplete;
    }

    @Override
    public String toString() {
        return (isComplete ? "[X] "+taskName : "[ ] "+taskName);
    }
}
