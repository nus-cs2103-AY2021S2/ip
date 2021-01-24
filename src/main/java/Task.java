import java.time.format.DateTimeFormatter;

//Tasks Class that is parent of deadline, Events and Todo
public abstract class Task {
    protected String taskName;
    protected boolean completed;

    protected DateTimeFormatter printDateFormat;
    protected DateTimeFormatter printTimeFormat;
    public Task(String taskName) {
        this.taskName = taskName;
        completed = false;

        this.printDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.printTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    }
    public void markCompleted(){
        completed = true;
    }

    @Override
    public String toString() {
        return (completed ? "[X] "+taskName : "[ ] "+taskName);
    }
}
