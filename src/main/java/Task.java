import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Task {
    protected static LinkedList<Task> Tasks = new LinkedList<Task>();
    protected static int numOfTasks = 0;
    protected String taskName;
    protected boolean isDone;
    protected final static DateTimeFormatter DF1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final static DateTimeFormatter DF2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    // Initializer (This is a test merge request)
    public Task(){
        taskName = "";
        isDone = false;
        numOfTasks += 1;
        Tasks.add(this);
    }

    public Task(String taskName){
        this.taskName = taskName;
        isDone = false;
        numOfTasks += 1;
        Tasks.add(this);
    }

    // Getter
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTaskName(){
        return this.taskName;
    }

    public static int getNumOfTasks(){
        return numOfTasks;
    }

    public static LinkedList<Task> getTasks(){
        return Tasks;
    }

    // Setter
    public void markAsDone(){
        this.isDone = true;
    }

    public void delete(){
        int taskIndex = Tasks.indexOf(this);
        Tasks.remove(taskIndex);
        numOfTasks -= 1;
    }


}
