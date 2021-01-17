public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description){
        this.description = description;
        this.isCompleted = false;
    }

    public void markComplete(){
        this.isCompleted = true;
    }

    @Override
    public String toString(){
        String status = isCompleted ? "X" : "";
        return "[" + status + "] " + description;
    }
}
