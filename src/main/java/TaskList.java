import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasksList;

    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    public void addTask(Task newTask){
        tasksList.add(newTask);
    }

    public Task getTask(int index){
        return tasksList.get(index);
    }

    public void displayTasks(){
        for(int i = 0; i < tasksList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + tasksList.get(i));
        }
    }
}
