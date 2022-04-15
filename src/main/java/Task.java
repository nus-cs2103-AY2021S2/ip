import java.time.format.DateTimeFormatter;


public class Task{
    private final Boolean isDone;
    protected final String name;
    protected DateTimeFormatter Date;
    protected DateTimeFormatter Time;

    /**
     * Constructor for tasks
     * @param name task name.
     * @param isDone task status.
     */

    Task(String name,Boolean isDone){
        this.isDone = isDone;
        this.name = name;
        this.Date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.Time = DateTimeFormatter.ofPattern("hh:mm");
    }

    /**
     * Get the name for the task
     * @return the task name.
     */

    String getName() {
        return this.name;
    }

    /**
     * Get the status for the task
     * @return the task status.
     */

    Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done
     * @return the new task.
     */

    Task finish(){
        return new Task(this.getName(), true) ;
    }

    @Override
    public String toString(){
        if(this.isDone){
            return "[X] " + this.getName();
        }
        else{
            return "[ ] " + this.getName();
        }
    }



}
