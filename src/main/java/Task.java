public class Task {
    protected String description;
    protected boolean isDone;
    public static int countTask ;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.countTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    public String getDescription(){
        return this.description;
    }
    public String getTaskInfo(){
        return getStatusIcon()+getDescription();
    }
    public void markAsDone(){
        this.isDone = true;
    }
}
