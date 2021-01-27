package duke.task;

import java.util.LinkedList;


public class TaskList {
    private LinkedList<Task> Tasks;
    private int numOfTasks;


    public TaskList(){
        Tasks = new LinkedList<Task>();
        numOfTasks = 0;
    }


    public int getNumOfTasks(){
        return numOfTasks;
    }


    public LinkedList<Task> getTasks(){
        return Tasks;
    }


    public void addTasks(Task task){
        Tasks.add(task);
        numOfTasks++;
    }

    public void delete(Task task){
        int taskIndex = Tasks.indexOf(task);
        Tasks.remove(taskIndex);
        numOfTasks -= 1;
    }


}
