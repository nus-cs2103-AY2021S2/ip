import java.time.LocalDate;
/**
   * Task is a generic type to categories the users input
   * @param description the description of the task
   * @param isDone specifies if the task should be marked as done
   */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
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
