package duke.task;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected final static DateTimeFormatter DF1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final static DateTimeFormatter DF2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    public Task(){
        taskName = "";
        isDone = false;
    }

    public Task(String taskName){
        this.taskName = taskName;
        isDone = false;
    }

    public Task(String taskName, boolean status){
        this.taskName = taskName;
        isDone = status;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getStatus(){
        return isDone;
    }

    public String getTaskName(){
        return this.taskName;
    }


    public String toString(){
        return this.taskName;
    }


    public void markAsDone(){
        this.isDone = true;
    }


}
