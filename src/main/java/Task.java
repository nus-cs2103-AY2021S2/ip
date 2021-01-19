import java.util.LinkedList;

public class Task {
    protected static LinkedList<Task> Tasks = new LinkedList<Task>();
    protected static int numOfTasks = 0;
    protected String taskName;
    protected boolean isDone;

    // Initializer
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
