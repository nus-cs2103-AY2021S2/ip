public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }
    public void markCompleted(){
        completed = true;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("[ ] "+taskName);
        if(completed)
            return "[X] "+taskName;
        else
            return "[ ] "+taskName;
    }
}
