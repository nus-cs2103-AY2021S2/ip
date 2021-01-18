public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }
    
    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String str = String.format("[%s] %s", (isCompleted ? "X" : " "), name);
        return str;
    }
}