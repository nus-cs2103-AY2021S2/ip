public class Task{
    protected String description;
    protected boolean isCompleted;

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public void markComplete(){
        this.isCompleted = true;
    }

    public String getFormattedData() {
        String status = isCompleted ? "1" : "0";
        return  status + " | " + description;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "X" : "";
        return "[" + status + "] " + description;
    }
}
