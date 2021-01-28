import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return X symbols if done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    LocalDate getTime(){
        return null;
    }

    @Override
    public String toString() {
        return "["+this.getStatusIcon()+"] "+ this.description;
    }

}
