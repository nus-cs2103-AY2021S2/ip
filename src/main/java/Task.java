public class Task {
    int id;
    String description;
    boolean isCompleted;

    public Task(int id, String description){
        this.id = id;
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
