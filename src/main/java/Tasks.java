import java.util.ArrayList;

public class Tasks {
    ArrayList<String> Tasks;

    //Initializes the tasklist
    Tasks(){
        Tasks = new ArrayList<String>();
    }

    //Adds a task to the task list.
    public void addTask(String task){
        Tasks.add(task);
        System.out.println("added: " + task + "\n");
    }

    //Prints all the tasks
    public void printTasks(){
        for(int j = 0; j < Tasks.size(); j++){
            System.out.println(j+1 + ". " + Tasks.get(j) + "\n");
        }
    }
}
